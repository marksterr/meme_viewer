package com.example.basicapplication.di

import com.example.basicapplication.data.IRepository
import com.example.basicapplication.data.MemeService
import com.example.basicapplication.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    fun provideRepository(service: MemeService): IRepository {
        return RepositoryImpl(service)
    }
}