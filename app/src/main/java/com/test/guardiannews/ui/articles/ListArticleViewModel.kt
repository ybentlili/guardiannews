package com.test.guardiannews.ui.articles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.guadiannews.domain.articles.ArticlesUseCase
import com.test.guardiannews.base.IModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListArticleViewModel(private val articlesUseCase: ArticlesUseCase) : ViewModel() , IModel<ArticlesPartialState, ArticlesIntent>{


    var state: MutableLiveData<ArticlesPartialState> = MutableLiveData()

    override fun dispatchIntent(intent: ArticlesIntent) {
        handleAction(intentToAction(intent))
    }

    private fun intentToAction(intent : ArticlesIntent) : ArticlesAction {
        return when(intent){
            is ArticlesIntent.GetListArticles -> ArticlesAction.GetListArticles(
                apiKey = intent.apiKey, showField = intent.showField,
                query = intent.query, pageSize = intent.pageSize
            )
            is ArticlesIntent.NotifyGetListArticlesExecuted -> ArticlesAction.ConsumeGetListArticles
        }
    }

    private fun handleAction(action: ArticlesAction) {
            when(action) {
                is ArticlesAction.GetListArticles -> {
                    state.value =
                        ArticlesPartialState.ListArticlesLoading(isLoading = true, isError = false)
                    articlesUseCase.getAllArticles(apiKey = action.apiKey, showField = action.showField, query = action.query, pageSize = action.pageSize)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                            {
                                state.postValue(ArticlesPartialState.ListArticlesSuccessufly(it))
                            },
                            {
                                state.postValue(ArticlesPartialState.ListArticlesError(it.message))
                            }
                        )
                }
                is ArticlesAction.ConsumeGetListArticles -> {
                    state.value = ArticlesPartialState.EventListArticlesConsumed
                }

            }
    }


}