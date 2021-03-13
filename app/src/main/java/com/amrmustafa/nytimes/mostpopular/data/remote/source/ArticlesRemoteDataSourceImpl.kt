package com.amrmustafa.nytimes.mostpopular.data.remote.source

import com.amrmustafa.nytimes.BuildConfig
import com.amrmustafa.nytimes.base.constants.Period
import com.amrmustafa.nytimes.base.di.qualifiers.CoroutinesIO
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import com.amrmustafa.nytimes.mostpopular.data.remote.services.ArticlesService


class ArticlesRemoteDataSourceImpl @Inject constructor(
    private val service: ArticlesService,
    @CoroutinesIO private val context: CoroutineContext
) : ArticlesRemoteDataSource {
    override suspend fun getArticlesAsync(period: Period) = withContext(context) {
        val response = service.getArticlesAsync(period.value, BuildConfig.API_KEY).await()

        if (response.isSuccessful)
            response.body()?.results ?: throw Exception("no nytimes articles")
        else
            throw Exception("couldn't fetch nytimes articles")
    }
}
