package com.amrmustafa.nytimes.mostpopular.data.local

import com.amrmustafa.nytimes.base.caching.LocalDatabaseClient
import com.amrmustafa.nytimes.base.caching.LocalDatabaseTypeConverter
import com.amrmustafa.nytimes.base.constants.Period
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ArticlesLocalDataSourceImpl @Inject constructor(val localDatabaseTypeConverter: LocalDatabaseTypeConverter) : ArticlesLocalDataSource {

    override suspend fun getArticles(period: Period) = withContext(Dispatchers.IO) {
        LocalDatabaseClient.appDatabase
            .articlesDao()?.getAllArticles(period.value)?.articleData?.let {
                localDatabaseTypeConverter.getArticles(it)
            } ?: emptyList()

    }


    override suspend fun saveAllArticles(articles: ArticlesEntity) =
        withContext(Dispatchers.IO) {
            LocalDatabaseClient.appDatabase
                .articlesDao()?.insertAll(articles)
        }

}