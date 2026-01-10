package com.example.myapptest1.domain.repository

import com.example.myapptest1.domain.model.DogFact

interface DogFactRepository {
    suspend fun getDogFact(): Result<DogFact>
}