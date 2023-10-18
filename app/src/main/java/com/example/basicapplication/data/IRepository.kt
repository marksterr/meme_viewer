package com.example.basicapplication.data

import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getMemes(): Flow<UIState>
}