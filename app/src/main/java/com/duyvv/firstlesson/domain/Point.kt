package com.duyvv.firstlesson.domain

data class Point(
    val x: Float,
    val y: Float,
) {
    fun isRootPoint(): Boolean = x == 0f && y == 0f
}
