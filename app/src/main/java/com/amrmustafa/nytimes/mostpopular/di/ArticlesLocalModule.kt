package com.amrmustafa.nytimes.mostpopular.di


import com.amrmustafa.nytimes.mostpopular.data.local.ArticlesLocalDataSource
import com.amrmustafa.nytimes.mostpopular.data.local.ArticlesLocalDataSourceImpl
import dagger.Binds
import dagger.Module


@Module(includes = [ArticlesLocalModule.Binders::class])
class ArticlesLocalModule {

    @Module
    interface Binders {
        @Binds
        fun bindsLocalSource(
            localDataSourceImpl: ArticlesLocalDataSourceImpl
        ): ArticlesLocalDataSource
    }

}