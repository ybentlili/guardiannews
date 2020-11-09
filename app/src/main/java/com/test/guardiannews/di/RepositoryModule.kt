package com.test.guardiannews.di

import com.test.guadiannews.domain.article.ArticleRepository
import com.test.guadiannews.domain.articles.ArticlesRepository
import com.test.guardiannews.data.articles.ArticleRepositoryImpl
import com.test.guardiannews.data.articles.ArticlesRepositoryImpl
import org.koin.dsl.module


val repositoryModule = module {
    single { ArticlesRepositoryImpl(get()) as ArticlesRepository}
    single { ArticleRepositoryImpl(get()) as ArticleRepository}
}