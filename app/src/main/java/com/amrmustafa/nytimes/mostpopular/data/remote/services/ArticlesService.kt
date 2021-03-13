package com.amrmustafa.nytimes.mostpopular.data.remote.services

import com.amrmustafa.nytimes.mostpopular.data.models.ArticlesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticlesService {

    @GET("{period}.json")
    fun getArticlesAsync(@Path("period") period: String, @Query("api-key") apiKey: String): Deferred<Response<ArticlesResponse>>

}