package com.test.guardiannews.data.model.articles

import com.google.gson.annotations.SerializedName

data class NewsData (
    @SerializedName("id") val id :String?,
    @SerializedName("type") val type :String?,
    @SerializedName("webTitle") val webTitle :String?,
    @SerializedName("sectionName") val sectionName : String?,
    @SerializedName("webPublicationDate") val webPublicationDate : String?,
    @SerializedName("fields") val fields: FiledNewsData?

)