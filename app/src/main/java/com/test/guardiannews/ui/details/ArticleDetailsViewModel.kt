package com.test.guardiannews.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.guadiannews.domain.article.ArticleRepository
import com.test.guadiannews.domain.article.ArticleUseCase
import com.test.guadiannews.domain.articles.ArticlesUseCase
import com.test.guardiannews.base.IModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticleDetailsViewModel(private val articleUseCase: ArticleUseCase) : ViewModel() , IModel<ArticleDetailsPartialState, ArticleDetailsIntent>{


    var state: MutableLiveData<ArticleDetailsPartialState> = MutableLiveData()

    override fun dispatchIntent(intent: ArticleDetailsIntent) {
        handleAction(intentToAction(intent))
    }

    private fun intentToAction(intent : ArticleDetailsIntent) : ArticleDetailAction {
        return when(intent){
            is ArticleDetailsIntent.GetArticleDetails -> ArticleDetailAction.GetArticleDetails(
                apiKey = intent.apiKey, showField = intent.showField,
                idArticle = intent.idArticle
            )
            is ArticleDetailsIntent.ConsumeGetArticleDetails -> ArticleDetailAction.ConsumeGetArticleDetails
        }
    }

    private fun handleAction(action: ArticleDetailAction) {
            when(action) {
                is ArticleDetailAction.GetArticleDetails -> {
                    state.value =
                            ArticleDetailsPartialState.GetArticleDetailsLoading(isLoading = true, isError = false)
                    articleUseCase.getDetailsArticle(apiKey = action.apiKey, showField = action.showField, idArticle = action.idArticle)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                            {
                                state.postValue(ArticleDetailsPartialState.ArticleDetailsSuccessufly(it))
                            },
                            {
                                state.postValue(ArticleDetailsPartialState.GetArticleDetailsError(it.message))
                            }
                        )
                }
                is ArticleDetailAction.ConsumeGetArticleDetails -> {
                    state.value = ArticleDetailsPartialState.EventDetailsArticleConsumed
                }

            }
    }


}