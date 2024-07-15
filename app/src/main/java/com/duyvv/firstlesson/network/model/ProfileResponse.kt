package com.duyvv.firstlesson.network.model


import com.duyvv.firstlesson.domain.History
import com.duyvv.firstlesson.domain.Profile
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("full_name")
    var fullName: String? = null,
    @SerializedName("history")
    var histories: List<HistoryResponse>? = null,
    var message: String? = null,
    var position: String? = null,
    @SerializedName("success")
    var isSuccess: Boolean? = null
) {
    fun toProfileModel() = Profile(
        fullName = fullName ?: "",
        histories = histories?.map { it.toHistoryModel() } ?: mutableListOf<History>(),
        position = position ?: "",
        isSuccess = isSuccess ?: false
    )
}

data class HistoryResponse(
    @SerializedName("is_up")
    var isUp: Boolean? = null,
    @SerializedName("title")
    var title: String? = null
) {
    fun toHistoryModel() = History(
        isUp = isUp ?: false,
        title = title ?: ""
    )
}