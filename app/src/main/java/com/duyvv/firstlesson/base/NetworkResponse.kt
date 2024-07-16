package com.duyvv.firstlesson.base

sealed class NetworkResponse<out T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(
        data: T,
    ) : NetworkResponse<T>(data = data)

    class Error(
        message: String,
    ) : NetworkResponse<Nothing>(message = message)
}
