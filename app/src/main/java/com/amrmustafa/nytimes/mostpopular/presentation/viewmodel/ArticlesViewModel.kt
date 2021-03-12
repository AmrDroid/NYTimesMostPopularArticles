package com.amrmustafa.nytimes.mostpopular.presentation.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrmustafa.nytimes.base.constants.Period
import com.amrmustafa.nytimes.base.models.APIResponse
import com.amrmustafa.nytimes.mostpopular.data.models.Article
import com.amrmustafa.nytimes.mostpopular.data.models.ArticlesResponse
import com.amrmustafa.nytimes.mostpopular.domain.ArticlesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class ArticlesViewModel @Inject constructor(
    private val mArticlesUseCase: ArticlesUseCase
) : ViewModel() {

    val articlesResult = MediatorLiveData<APIResponse<List<Article>>>()
    var article: Article? = null

    fun getArticlesAysnc(period: Period = Period.DAILY, forceNetwork: Boolean = false) {
        viewModelScope.launch {
            articlesResult.addSource(mArticlesUseCase.getArticlesAsync(period, forceNetwork)) {
                articlesResult.value = it
            }
        }
    }


}