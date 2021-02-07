package com.nurlandroid.kotapp.feature.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nurlandroid.kotapp.common.base.BaseViewModel
import com.nurlandroid.kotapp.common.error.ErrorType
import com.nurlandroid.kotapp.common.network.ResponseStatus

class PostViewModel(private val postRepository: PostRepository) : BaseViewModel() {

    private val mutableLiveData = MutableLiveData<UiState>()
    val posts: LiveData<UiState> = mutableLiveData

    sealed class UiState {
        object Loading : UiState()
        class Data(val dataList: List<Post>) : UiState()
        class Error(val errorType: ErrorType) : UiState()
    }

    init {
        loadSomeData()
    }

    private fun loadSomeData() {
        mutableLiveData.postValue(UiState.Loading)

        doInMainThread {
            val result = postRepository.loadData()

            when (result.status) {
                ResponseStatus.SUCCESS -> mutableLiveData.postValue(UiState.Data(result.fetchedData!!))
                ResponseStatus.ERROR -> mutableLiveData.postValue(UiState.Error(result.errorType!!))
            }
        }
    }
}