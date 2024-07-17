package com.duyvv.firstlesson.network.api

import com.duyvv.firstlesson.network.model.ProfileResponse
import retrofit2.http.GET

interface ProfileApi {
    @GET("d/af3cc8b64fa487da8be34c1cc1c5d2d5.json")
    suspend fun getProfile(): ProfileResponse
}
