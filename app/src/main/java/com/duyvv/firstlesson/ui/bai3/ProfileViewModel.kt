package com.duyvv.firstlesson.ui.bai3

import androidx.lifecycle.viewModelScope
import com.duyvv.firstlesson.base.BaseViewModel
import com.duyvv.firstlesson.base.NetworkResponse
import com.duyvv.firstlesson.domain.Profile
import com.duyvv.firstlesson.repository.ProfileRepository
import com.duyvv.firstlesson.ui.common.BGType
import com.duyvv.firstlesson.utils.app.AppConstants.DEFAULT_MESSAGE_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseViewModel() {
    private val _profile = MutableStateFlow(Profile())
    val profile = _profile.asStateFlow()

    fun getProfile() {
        viewModelScope.launch {
            showLoading(true)
            delay(2000)

            // get data
            when (val response = profileRepository.getProfile()) {
                is NetworkResponse.Success -> {
                    _profile.value = response.body!!.toProfileModel()
                }

                is NetworkResponse.Error ->
                    handleMessage(
                        response.message ?: DEFAULT_MESSAGE_ERROR,
                        BGType.BG_TYPE_ERROR
                    )
            }

            showLoading(false)
        }
    }
}
