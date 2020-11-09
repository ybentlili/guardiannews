package com.test.guardiannews.di

import com.test.guadiannews.domain.article.ArticleUseCase
import com.test.guadiannews.domain.articles.ArticlesUseCase
import org.koin.dsl.module


val useCaseModule = module {
    factory { ArticlesUseCase(get()) }
    factory { ArticleUseCase(get()) }
}