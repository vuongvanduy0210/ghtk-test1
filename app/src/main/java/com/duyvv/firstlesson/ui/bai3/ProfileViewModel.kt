package com.duyvv.firstlesson.ui.bai3

import androidx.lifecycle.viewModelScope
import com.duyvv.firstlesson.base.BaseViewModel
import com.duyvv.firstlesson.domain.Profile
import com.duyvv.firstlesson.network.model.ProfileResponse
import com.duyvv.firstlesson.ui.common.BGType
import com.duyvv.firstlesson.utils.app.AppConstants
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.InputStream
import java.io.InputStreamReader

class ProfileViewModel : BaseViewModel() {
    private val _profile = MutableStateFlow(Profile())
    val profile = _profile.asStateFlow()

    fun fetchData(inputStream: InputStream) {
        viewModelScope.launch {
            showLoading(true)
            delay(2000)

            // get data
            try {
                val gson = Gson()
                val reader = InputStreamReader(inputStream)
                val data = gson.fromJson(reader, ProfileResponse::class.java)
                _profile.value = data.toProfileModel()
                reader.close()
            } catch (e: Exception) {
                handleMessage(
                    e.localizedMessage ?: AppConstants.DEFAULT_MESSAGE_ERROR,
                    BGType.BG_TYPE_ERROR,
                )
            }

            showLoading(false)
        }
    }
}
