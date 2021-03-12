package com.amrmustafa.nytimes.mostpopular.di


import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import com.amrmustafa.nytimes.mostpopular.data.remote.source.ArticlesRemoteDataSource
import com.amrmustafa.nytimes.mostpopular.data.remote.source.ArticlesRemoteDataSourceImpl
import com.amrmustafa.nytimes.mostpopular.data.remote.services.ArticlesService


@Module(includes = [ArticlesRemoteModule.Binders::class])
class ArticlesRemoteModule {

    @Module
    interface Binders {
        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: ArticlesRemoteDataSourceImpl
        ): ArticlesRemoteDataSource
    }

    @Provides
    fun providesArticlesService(retrofit: Retrofit): ArticlesService =
        retrofit.create(ArticlesService::class.java)


}