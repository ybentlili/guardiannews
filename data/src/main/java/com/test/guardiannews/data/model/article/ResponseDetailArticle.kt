package com.test.guardiannews.data.model.article

import com.google.gson.annotations.SerializedName

data class ResponseDetailArticle (

    @SerializedName("response") val response : ContentDataArticle
)