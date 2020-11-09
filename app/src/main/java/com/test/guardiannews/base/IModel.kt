package com.test.guardiannews.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface IModel<STATE,INTENT> {
    fun dispatchIntent(intent : INTENT)
}