package com.test.guardiannews.ui.articles

import android.content.Context
import com.test.guadiannews.domain.model.ListArticle

data class ListArticlesState (
    val isLoading : Boolean,
    val isError   :Boolean ,
    val listArticles : ListArticle?,
    val errorMessage : String?
)