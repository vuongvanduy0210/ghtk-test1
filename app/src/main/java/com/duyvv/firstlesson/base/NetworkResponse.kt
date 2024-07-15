package com.duyvv.firstlesson.base

sealed class NetworkResponse<out T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : NetworkResponse<T>(data = data)

    class Error(message: String) : NetworkResponse<Nothing>(message = message)
}

data class ResponseMessage(
    val message: String,
    val bgType: BGType
)

enum class BGType {
    BG_TYPE_NORMAL,
    BG_TYPE_SUCCESS,
    BG_TYPE_WARNING,
    BG_TYPE_ERROR
}

