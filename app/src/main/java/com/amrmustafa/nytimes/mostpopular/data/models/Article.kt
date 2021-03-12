package com.amrmustafa.nytimes.mostpopular.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    @SerializedName("id")
    var id: Long,
    @SerializedName("title")
    var title: String,
    @SerializedName("byline")
    val byline: String,
    @SerializedName("abstract")
    var abstract: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("published_date")
    var publishedDate: String,
    @SerializedName("media")
    var media: List<Media>
): Parcelable