package com.test.guardiannews.di

import com.test.guardiannews.ui.articles.ListArticleViewModel
import com.test.guardiannews.ui.details.ArticleDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel{ ListArticleViewModel(articlesUseCase = get()) }
    viewModel { ArticleDetailsViewModel(articleUseCase = get()) }
}