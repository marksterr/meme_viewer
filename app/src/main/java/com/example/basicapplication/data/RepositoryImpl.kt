package com.example.basicapplication.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(private val service: MemeService): IRepository {
    override fun getMemes(): Flow<UIState> {
        return flow {
            val response = service.getMemes()

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.Success(it))
                } ?: emit(UIState.Failure(response.message()))
            }
        }
    }
}