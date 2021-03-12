package com.amrmustafa.nytimes.mostpopular.data.remote.source

import com.amrmustafa.nytimes.base.constants.Period
import com.amrmustafa.nytimes.mostpopular.data.models.Article


interface ArticlesRemoteDataSource {

    suspend fun getArticlesAsync(period: Period): List<Article>

}