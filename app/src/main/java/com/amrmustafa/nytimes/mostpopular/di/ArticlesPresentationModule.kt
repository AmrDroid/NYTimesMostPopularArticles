package com.amrmustafa.nytimes.mostpopular.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amrmustafa.nytimes.base.components.ViewModelFactory
import com.amrmustafa.nytimes.base.di.modules.ViewModelKey
import com.amrmustafa.nytimes.mostpopular.presentation.viewmodel.ArticlesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ArticlesPresentationModule {
    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    abstract fun bindsArticlesViewModel(mArticlesViewModel: ArticlesViewModel): ViewModel
}