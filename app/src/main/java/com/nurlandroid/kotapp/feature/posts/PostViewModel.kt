package com.nurlandroid.kotapp.feature.posts

import androidx.lifecycle.viewModelScope
import com.nurlandroid.kotapp.common.ui.UiState
import com.nurlandroid.kotapp.common.base.BaseViewModel
import com.nurlandroid.kotapp.common.network.ResponseStatus
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Post>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadSomeData()
    }

    private fun loadSomeData() {
        doWorkInMainThread(
                doAsyncBlock = {
                    _uiState.emit(UiState.Loading)

                    val resultDeferred = async { postRepository.loadData() }
                    val result = resultDeferred.await()

                    when (result.status) {
                        ResponseStatus.SUCCESS -> _uiState.emit(UiState.Data(result.fetchedData!!))
                        ResponseStatus.ERROR -> _uiState.emit(UiState.Error(result.errorType!!))
                    }
                },
                exceptionBlock = {
                    viewModelScope.launch { _uiState.emit(UiState.Error(it)) }
                }
        )
    }
}