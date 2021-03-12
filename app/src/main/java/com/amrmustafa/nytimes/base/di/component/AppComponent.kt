package com.amrmustafa.nytimes.base.di.component

import android.app.Application
import com.amrmustafa.nytimes.base.ArticlesApp
import com.amrmustafa.nytimes.base.di.modules.*
import com.amrmustafa.nytimes.mostpopular.di.ArticlesDomainModule
import com.amrmustafa.nytimes.mostpopular.di.ArticlesLocalModule
import com.amrmustafa.nytimes.mostpopular.di.ArticlesPresentationModule
import com.amrmustafa.nytimes.mostpopular.di.ArticlesRemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        NetworkModule::class, ContextModule::class,
        ActivityModule::class,
        FragmentModule::class,
        SchedulersModule::class,
        ArticlesDomainModule::class,
        ArticlesPresentationModule::class,
        ArticlesRemoteModule::class,
        ArticlesLocalModule::class]
)
interface AppComponent {

    fun inject(app: ArticlesApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}