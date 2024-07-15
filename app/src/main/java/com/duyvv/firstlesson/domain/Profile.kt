package com.duyvv.firstlesson.domain

data class Profile(
    val isSuccess: Boolean? = null,
    val fullName: String? = null,
    val position: String? = null,
    val histories: List<History>? = null
)