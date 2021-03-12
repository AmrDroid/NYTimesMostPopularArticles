package com.amrmustafa.nytimes.mostpopular.data.repository


import androidx.lifecycle.liveData
import com.amrmustafa.nytimes.base.caching.LocalDatabaseTypeConverter
import com.amrmustafa.nytimes.base.constants.Period
import com.amrmustafa.nytimes.base.models.APIResponse
import com.amrmustafa.nytimes.mostpopular.data.local.ArticlesEntity
import com.amrmustafa.nytimes.mostpopular.data.local.ArticlesLocalDataSource
import com.amrmustafa.nytimes.mostpopular.data.models.Article
import javax.inject.Inject
import com.amrmustafa.nytimes.mostpopular.data.remote.source.ArticlesRemoteDataSource

class ArticlesRepositoryImpl @Inject constructor(
    private val remoteDataSource: ArticlesRemoteDataSource,
    private val localDataSource: ArticlesLocalDataSource,
    private val localDatabaseTypeConverter: LocalDatabaseTypeConverter
) : ArticlesRepository {

    override suspend fun getArticlesAsync(
        period: Period,
        forceNetwork: Boolean
    ) = liveData {
        try {
            emit(APIResponse.loading())

            var articles: List<Article>? = null

            if (!forceNetwork) {
                articles = localDataSource.getArticles(period)
            }


            if (articles.isNullOrEmpty()) {
                articles = remoteDataSource.getArticlesAsync(period)
                val newsArticleEntity =
                    ArticlesEntity(
                        period.value,
                        localDatabaseTypeConverter.fromArticles(articles)
                    )
                localDataSource.saveAllArticles(newsArticleEntity)
            }

            emit(APIResponse.success(articles))

        } catch (exception: Exception) {
            emit(APIResponse.error(exception, null))
        }
    }
}