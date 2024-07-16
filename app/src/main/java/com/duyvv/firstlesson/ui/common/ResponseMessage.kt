package com.duyvv.firstlesson.ui.common

data class ResponseMessage(
    val message: String,
    val bgType: BGType,
)

enum class BGType {
    BG_TYPE_NORMAL,
    BG_TYPE_SUCCESS,
    BG_TYPE_WARNING,
    BG_TYPE_ERROR,
}