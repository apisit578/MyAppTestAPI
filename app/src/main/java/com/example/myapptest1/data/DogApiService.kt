package com.example.myapptest1.data

import com.example.myapptest1.data.dto.DogFactResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApiService {
    @GET("api/v2/facts")
    suspend fun getDogFacts(
        @Query("limit") limit: Int = 1
    ): DogFactResponseDto
}