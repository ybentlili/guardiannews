package com.test.guadiannews.domain.model

data class ListArticle (
    val total : Int,
    val numberPage : Int,
    val listArticle : List<Article>
)