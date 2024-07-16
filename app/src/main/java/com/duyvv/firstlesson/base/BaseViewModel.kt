package com.duyvv.firstlesson.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duyvv.firstlesson.ui.common.BGType
import com.duyvv.firstlesson.ui.common.ResponseMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    val isLoading = MutableSharedFlow<Boolean>()
    val responseMessage = MutableSharedFlow<ResponseMessage>()

    fun showLoading(isShow: Boolean) {
        viewModelScope.launch {
            isLoading.emit(isShow)
        }
    }

    fun handleMessage(
        message: String,
        bgType: BGType,
    ) {
        viewModelScope.launch {
            responseMessage.emit(
                ResponseMessage(
                    message = message,
                    bgType = bgType,
                ),
            )
        }
    }
}
