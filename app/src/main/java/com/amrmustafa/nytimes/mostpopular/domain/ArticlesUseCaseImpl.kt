package com.amrmustafa.nytimes.mostpopular.domain

import com.amrmustafa.nytimes.base.constants.Period
import com.amrmustafa.nytimes.mostpopular.data.repository.ArticlesRepository
import javax.inject.Inject

class ArticlesUseCaseImpl @Inject constructor(private val mArticlesRepository: ArticlesRepository) :
    ArticlesUseCase {

    override suspend fun getArticlesAsync(
        period: Period,
        forceNetwork: Boolean
    ) = mArticlesRepository.getArticlesAsync(period, forceNetwork)

}
