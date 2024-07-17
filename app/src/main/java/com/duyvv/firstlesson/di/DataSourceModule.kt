package com.duyvv.firstlesson.di

import com.duyvv.firstlesson.datasource.remote.ProfileDataSource
import com.duyvv.firstlesson.datasource.remote.ProfileDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideProfileDataSource(profileDataSourceImpl: ProfileDataSourceImpl): ProfileDataSource {
        return profileDataSourceImpl
    }
}