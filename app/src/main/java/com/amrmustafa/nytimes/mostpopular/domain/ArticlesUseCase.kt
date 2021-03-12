package com.amrmustafa.nytimes.mostpopular.domain

import androidx.lifecycle.LiveData
import com.amrmustafa.nytimes.base.constants.Period
import com.amrmustafa.nytimes.base.models.APIResponse
import com.amrmustafa.nytimes.mostpopular.data.models.Article

interface ArticlesUseCase {

    suspend fun getArticlesAsync(period: Period, forceNetwork: Boolean): LiveData<APIResponse<List<Article>>>
}