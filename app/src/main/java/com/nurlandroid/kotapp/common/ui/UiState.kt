package com.nurlandroid.kotapp.common.ui

import com.nurlandroid.kotapp.common.error.ErrorType

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Data<T>(var data: T) : UiState<T>()
    data class Error(val errorType: ErrorType) : UiState<Nothing>()
}