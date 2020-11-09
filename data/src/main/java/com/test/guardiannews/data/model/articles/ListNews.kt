package com.test.guardiannews.data.model.articles

import com.google.gson.annotations.SerializedName

data class ListNews (
    @SerializedName("total") val total : Int,
    @SerializedName("currentPage") val currentPage : Int,
    @SerializedName ("pages") val pages :Int,
    @SerializedName("results") val listNews: List<NewsData>
)