package com.nurlandroid.kotapp.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nurlandroid.kotapp.common.base.BaseViewModel

class CommonViewModel : BaseViewModel() {

    sealed class Action {
        object MoveToMain : Action()
        object CloseCommonProfile : Action()
    }

    sealed class CommonState {
        object DataLoaded : CommonState()
    }

    data class DataLoadedStatus(
            var isCatalogLoaded: Boolean = false,
            var isGuestTokenLoaded: Boolean = false
    )

    private val _actionLiveData = MutableLiveData<Action>()
    val actionLiveData: LiveData<Action>
        get() = _actionLiveData

    private var dataLoadedStatus: DataLoadedStatus = DataLoadedStatus()

    fun startAction(action: Action) {
        _actionLiveData.postValue(action)
    }

    fun moveToMain() {
        _actionLiveData.postValue(Action.MoveToMain)
    }
}