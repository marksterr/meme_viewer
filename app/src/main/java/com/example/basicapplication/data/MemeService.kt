package com.example.basicapplication.data

import retrofit2.Response
import retrofit2.http.GET

interface MemeService {
    @GET(NetworkConstants.ENDPOINT)
    suspend fun getMemes(): Response<MemesResponse>
}