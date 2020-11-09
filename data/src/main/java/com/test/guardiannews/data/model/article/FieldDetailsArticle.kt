package com.test.guardiannews.data.model.article

import com.google.gson.annotations.SerializedName

data class FieldDetailsArticle (
    @SerializedName("headline") val headline :String?,
    @SerializedName("main") val main: String?,
    @SerializedName("body") val body :String?,
    @SerializedName("thumbnail") val thumbnail:String?
)