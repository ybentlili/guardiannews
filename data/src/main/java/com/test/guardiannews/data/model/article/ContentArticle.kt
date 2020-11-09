package com.test.guardiannews.data.model.article

import com.google.gson.annotations.SerializedName

data class ContentArticle(
    @SerializedName("id")  val id :String,
    @SerializedName("sectionName") val sectionName :String,
    @SerializedName("webTitle") val webTitle :String,
    @SerializedName("webPublicationDate") val webPublicationDate : String,
    @SerializedName("fields") val fields :FieldDetailsArticle,
)