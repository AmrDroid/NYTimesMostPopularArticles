package com.amrmustafa.nytimes.mostpopular.data.local

import com.amrmustafa.nytimes.base.constants.Period
import com.amrmustafa.nytimes.mostpopular.data.models.Article


interface ArticlesLocalDataSource {

    suspend fun saveAllArticles(articles: ArticlesEntity): Unit?
    suspend fun getArticles(period: Period): List<Article>
}