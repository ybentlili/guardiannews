package com.test.guardiannews.data.model.articles

import com.google.gson.annotations.SerializedName

data class FiledNewsData(
    @SerializedName("thumbnail") val thumbnail : String
)