package com.amrmustafa.nytimes.base.caching

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.amrmustafa.nytimes.mostpopular.data.models.Article
import javax.inject.Inject

class LocalDatabaseTypeConverter @Inject constructor(val gson: Gson) {
    fun getArticles(value: String): List<Article> {
        val listType =
            object : TypeToken<List<Article>>() {}.type
        return gson.fromJson(value, listType)
    }

    fun fromArticles(articles: List<Article>): String {
        return gson.toJson(articles)
    }
}