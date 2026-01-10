package com.example.myapptest1.data.repository

import com.example.myapptest1.data.DogApiService
import com.example.myapptest1.data.dto.toDomain
import com.example.myapptest1.domain.model.DogFact
import com.example.myapptest1.domain.repository.DogFactRepository
import java.io.IOException

class DogFactRepositoryImp(
    private val api: DogApiService
): DogFactRepository {
    override suspend fun getDogFact(): Result<DogFact> {
        return try {
            val response = api.getDogFacts(limit = 1)

            val fact = response.data.firstOrNull()
                ?: return Result.failure(exception = Exception("No facts found"))

            Result.success(fact.toDomain())

        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}