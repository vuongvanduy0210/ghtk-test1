package com.duyvv.firstlesson.base

sealed class NetworkResponse<out T>(
    val body: T? = null,
    val message: String? = null,
) {
    class Success<T>(body: T) : NetworkResponse<T>(body = body)

    class Error(message: String) : NetworkResponse<Nothing>(message = message)
}
