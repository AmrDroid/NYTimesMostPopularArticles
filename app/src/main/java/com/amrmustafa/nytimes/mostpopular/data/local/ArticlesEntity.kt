package com.amrmustafa.nytimes.mostpopular.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
data class ArticlesEntity(
    @PrimaryKey
    var period: String,
    var articleData: String? = null,
    var currentDate: Long? = Date().time

) : Parcelable