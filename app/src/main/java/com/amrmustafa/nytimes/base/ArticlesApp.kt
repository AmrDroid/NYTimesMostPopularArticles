package com.amrmustafa.nytimes.base
import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.amrmustafa.nytimes.base.caching.LocalDatabase
import com.amrmustafa.nytimes.base.caching.LocalDatabaseClient
import com.amrmustafa.nytimes.base.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class ArticlesApp : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        LocalDatabaseClient.appDatabase =
            Room.databaseBuilder(applicationContext, LocalDatabase::class.java, "nytimes.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

}