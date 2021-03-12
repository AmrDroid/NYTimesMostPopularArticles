package com.amrmustafa.nytimes.mostpopular.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: ArticlesEntity)

    @Query("SELECT * from ArticlesEntity WHERE period= :period")
    fun getAllArticles(period: String): ArticlesEntity
}