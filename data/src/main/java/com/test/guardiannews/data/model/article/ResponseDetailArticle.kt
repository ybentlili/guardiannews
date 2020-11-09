package com.test.guardiannews.data.model.article

import com.google.gson.annotations.SerializedName
import com.test.guadiannews.domain.model.DetailArticle

data class ResponseDetailArticle (

    @SerializedName("response") val response : ContentDataArticle
)