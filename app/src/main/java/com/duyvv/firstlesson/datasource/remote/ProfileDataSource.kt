package com.duyvv.firstlesson.datasource.remote

import com.duyvv.firstlesson.base.BaseRemoteDataSource
import com.duyvv.firstlesson.base.NetworkResponse
import com.duyvv.firstlesson.network.api.ProfileApi
import com.duyvv.firstlesson.network.model.ProfileResponse
import javax.inject.Inject

interface ProfileDataSource {
    suspend fun getProfile(): NetworkResponse<ProfileResponse>
}

class ProfileDataSourceImpl @Inject constructor(
    private val profileApi: ProfileApi
) : BaseRemoteDataSource(), ProfileDataSource {

    override suspend fun getProfile(): NetworkResponse<ProfileResponse> {
        return safeCallApi {
            profileApi.getProfile()
        }
    }
}
