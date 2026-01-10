package com.example.myapptest1.app

import com.example.myapptest1.data.DogApiService
import com.example.myapptest1.data.repository.DogFactRepositoryImp
import com.example.myapptest1.domain.repository.DogFactRepository
import com.example.myapptest1.domain.usecase.GetDogFactUseCase
import com.example.myapptest1.domain.usecase.GetDogFactUseCaseImp
import com.example.myapptest1.presentation.factory.DogFactViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppContainer {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://dogapi.dog/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val api: DogApiService by lazy {
        retrofit.create(DogApiService::class.java)
    }

    private val repository: DogFactRepository by lazy {
        DogFactRepositoryImp(api)
    }

    private val getDogFactUseCase: GetDogFactUseCase by lazy {
        GetDogFactUseCaseImp(repository)
    }

    fun provideDogFactViewModelFactory(): DogFactViewModelFactory {
        return DogFactViewModelFactory(getDogFactUseCase)
    }
}

