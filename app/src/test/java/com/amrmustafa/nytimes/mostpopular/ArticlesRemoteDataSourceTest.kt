package com.amrmustafa.nytimes.mostpopular

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.amrmustafa.nytimes.MainCoroutineRule
import com.amrmustafa.nytimes.TestData.response
import com.amrmustafa.nytimes.base.constants.APIKey
import com.amrmustafa.nytimes.base.constants.EndpointUrl
import com.amrmustafa.nytimes.base.constants.Period
import com.amrmustafa.nytimes.mostpopular.data.remote.services.ArticlesService
import com.amrmustafa.nytimes.mostpopular.data.remote.source.ArticlesRemoteDataSource
import com.amrmustafa.nytimes.mostpopular.data.remote.source.ArticlesRemoteDataSourceImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response


class ArticlesRemoteDataSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var remoteDataSource: ArticlesRemoteDataSource

    lateinit var service: ArticlesService

    @Before
    fun init() {
        service = mock {
            onBlocking {
                getArticlesAsync(Period.DAILY.value, APIKey)
            } doReturn GlobalScope.async {
                Response.success(response)
            }
        }
        remoteDataSource = ArticlesRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)
    }

    @Test
    fun testGetArticlesSuccessResponse() = runBlocking {
        val result = remoteDataSource.getArticlesAsync(Period.DAILY)
        assert(result== response.results)

    }
}


