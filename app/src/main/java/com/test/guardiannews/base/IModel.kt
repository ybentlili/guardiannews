package com.test.guardiannews.base


interface IModel<STATE,INTENT> {
    fun dispatchIntent(intent : INTENT)
}