package com.duyvv.firstlesson.ui.bai3

import androidx.lifecycle.viewModelScope
import com.duyvv.firstlesson.base.BaseViewModel
import com.duyvv.firstlesson.domain.Profile
import com.duyvv.firstlesson.network.model.ProfileResponse
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : BaseViewModel() {

    private val _profile = MutableStateFlow(Profile())
    val profile = _profile.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            showLoading(true)
            // get data
            delay(2000)
            val jsonString = """
    {
  "success":true,
  "message":"",
  "full_name":"Nguyen Quang Chinh",
  "position":"Top Expert",
  "history":[
    {
      "title":"2 năm làm việc",
      "is_up":true
    },
    {
      "title":"3 năm làm việc",
      "is_up":true
    },
    {
      "title":"4 năm làm việc",
      "is_up":true
    },
    {
      "title":"Đề suất lên vị trí Top Expert",
      "is_up":true
    },
    {
      "title":"Giáng chức xuống làm dân thường",
      "is_up":false
    }
  ]
}
"""
            val gson = Gson()
            val data = gson.fromJson(jsonString, ProfileResponse::class.java)
            _profile.value = data.toProfileModel()
            showLoading(false)
        }
    }
}