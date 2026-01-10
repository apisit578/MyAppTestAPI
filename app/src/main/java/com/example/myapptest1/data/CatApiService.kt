package com.example.myapptest1.data

import com.example.myapptest1.data.dto.CatResponseDto
import retrofit2.http.GET

interface CatApiService {
//    https://api.thecatapi.com/v1/images/search
    @GET("v1/images/search")
    suspend fun getCat(): List<CatResponseDto>
}