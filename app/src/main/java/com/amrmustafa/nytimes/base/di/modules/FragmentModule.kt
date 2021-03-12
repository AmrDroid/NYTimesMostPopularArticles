package com.amrmustafa.nytimes.base.di.modules

import com.amrmustafa.nytimes.mostpopular.presentation.ui.fragments.ArticlesDetailsFragment
import com.amrmustafa.nytimes.mostpopular.presentation.ui.fragments.ArticlesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @get:ContributesAndroidInjector
    val articlesFragment: ArticlesFragment?

    @get:ContributesAndroidInjector
    val articlesDetailsFragment: ArticlesDetailsFragment?

}