package com.duyvv.firstlesson.repository

import com.duyvv.firstlesson.base.NetworkResponse
import com.duyvv.firstlesson.datasource.remote.ProfileDataSource
import com.duyvv.firstlesson.network.model.ProfileResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ProfileRepository {
    suspend fun getProfile(): NetworkResponse<ProfileResponse>
}

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {

    override suspend fun getProfile() = withContext(Dispatchers.IO) {
        profileDataSource.getProfile()
    }
}
