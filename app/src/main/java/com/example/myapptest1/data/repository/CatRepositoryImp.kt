package com.example.myapptest1.data.repository

import com.example.myapptest1.data.CatApiService
import com.example.myapptest1.data.dto.toDomain
import com.example.myapptest1.domain.model.CatModel
import com.example.myapptest1.domain.repository.CatRepository

class CatRepositoryImp(private val api: CatApiService) : CatRepository {
    override suspend fun getCat(): Result<CatModel> {
        return try {
            val response = api.getCat()

            val catData = response.firstOrNull()
                ?: return Result.failure(exception = Exception("No facts found"))

            Result.success(value = catData.toDomain())

        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}