package com.amrmustafa.nytimes.base.caching

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amrmustafa.nytimes.mostpopular.data.local.ArticlesDao
import com.amrmustafa.nytimes.mostpopular.data.local.ArticlesEntity

@Database(entities = [ArticlesEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticlesDao?
}