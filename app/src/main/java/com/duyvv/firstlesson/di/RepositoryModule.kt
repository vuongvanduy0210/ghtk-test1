package com.duyvv.firstlesson.di

import com.duyvv.firstlesson.repository.ProfileRepository
import com.duyvv.firstlesson.repository.ProfileRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository {
        return profileRepositoryImpl
    }
}