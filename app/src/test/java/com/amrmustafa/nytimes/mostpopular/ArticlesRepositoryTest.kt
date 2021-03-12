package com.amrmustafa.nytimes.mostpopular

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.amrmustafa.nytimes.LiveDataTestingUtils
import com.amrmustafa.nytimes.MainCoroutineRule
import com.amrmustafa.nytimes.TestData.response
import com.amrmustafa.nytimes.base.caching.LocalDatabaseTypeConverter
import com.amrmustafa.nytimes.base.constants.Period
import com.amrmustafa.nytimes.base.models.Status
import com.amrmustafa.nytimes.mostpopular.data.local.ArticlesLocalDataSource
import com.amrmustafa.nytimes.mostpopular.data.remote.source.ArticlesRemoteDataSource
import com.amrmustafa.nytimes.mostpopular.data.repository.ArticlesRepository
import com.amrmustafa.nytimes.mostpopular.data.repository.ArticlesRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class ArticlesRepositoryTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var repository: ArticlesRepository

    @Mock
    lateinit var remoteDataSource: ArticlesRemoteDataSource

    @Mock
    lateinit var localDataSource: ArticlesLocalDataSource



    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        repository =
            ArticlesRepositoryImpl(remoteDataSource, localDataSource, LocalDatabaseTypeConverter(Gson()))
    }

    @Test
    fun testGetArticlesSuccessResponse() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(remoteDataSource.getArticlesAsync(Period.DAILY))
            .thenReturn(response.results)
        val result = repository.getArticlesAsync(Period.DAILY, true)
        assert(LiveDataTestingUtils.getValue(result).status == Status.LOADING)
        assert(LiveDataTestingUtils.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestingUtils.getValue(result).data == response.results)
    }
}
