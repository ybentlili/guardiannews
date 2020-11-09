package com.test.guardiannews.data.model.articles

import com.google.gson.annotations.SerializedName

data class ResponseListNewsData(
    @SerializedName("response") var response: ListNews
)