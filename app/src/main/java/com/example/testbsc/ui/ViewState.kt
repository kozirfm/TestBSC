package com.example.testbsc.ui

sealed class ViewState
object Loading : ViewState()
data class Data<T>(val data: T) : ViewState()
data class Error(val t: Throwable?) : ViewState()