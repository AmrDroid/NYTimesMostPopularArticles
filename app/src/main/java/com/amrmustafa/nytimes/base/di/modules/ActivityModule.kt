package com.amrmustafa.nytimes.base.di.modules

import com.amrmustafa.nytimes.mostpopular.presentation.ui.activities.ArticlesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @get:ContributesAndroidInjector
    val articlesActivity: ArticlesActivity?

}