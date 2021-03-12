package com.amrmustafa.nytimes.mostpopular.di

import com.amrmustafa.nytimes.mostpopular.data.repository.ArticlesRepository
import com.amrmustafa.nytimes.mostpopular.data.repository.ArticlesRepositoryImpl
import com.amrmustafa.nytimes.mostpopular.domain.ArticlesUseCaseImpl
import com.amrmustafa.nytimes.mostpopular.domain.ArticlesUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class ArticlesDomainModule {

    @Binds
    abstract fun bindsRepository(
        repoImpl: ArticlesRepositoryImpl
    ): ArticlesRepository


    @Binds
    abstract fun bindsArtilesUseCase(
        mArticlesUseCase: ArticlesUseCaseImpl
    ): ArticlesUseCase


}