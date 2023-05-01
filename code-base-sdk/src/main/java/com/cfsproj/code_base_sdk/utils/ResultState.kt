package com.cfsproj.code_base_sdk.utils


sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error(val error: Exception) : UIState<Nothing>()
}