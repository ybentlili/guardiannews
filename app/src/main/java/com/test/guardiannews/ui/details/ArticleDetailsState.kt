package com.test.guardiannews.ui.details

import android.content.Context
import com.test.guadiannews.domain.model.DetailArticle
import com.test.guadiannews.domain.model.ListArticle

data class ArticleDetailsState (
    val isLoading : Boolean,
    val isError   :Boolean ,
    val detailArticle: DetailArticle?,
    val errorMessage : String?
)