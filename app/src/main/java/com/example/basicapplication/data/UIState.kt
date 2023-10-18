package com.example.basicapplication.data

sealed class UIState {
    data class Success(val data: MemesResponse): UIState()
    data class Failure(val errorMessage: String): UIState()
}
