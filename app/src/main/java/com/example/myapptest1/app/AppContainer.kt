package com.example.myapptest1.app

import com.example.myapptest1.data.CatApiService
import com.example.myapptest1.data.DogApiService
import com.example.myapptest1.data.repository.CatRepositoryImp
import com.example.myapptest1.data.repository.DogFactRepositoryImp
import com.example.myapptest1.domain.repository.CatRepository
import com.example.myapptest1.domain.repository.DogFactRepository
import com.example.myapptest1.domain.usecase.GetCatUseCase
import com.example.myapptest1.domain.usecase.GetCatUseCaseImp
import com.example.myapptest1.domain.usecase.GetDogFactUseCase
import com.example.myapptest1.domain.usecase.GetDogFactUseCaseImp
import com.example.myapptest1.presentation.factory.DogFactViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppContainer {

    val dogRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://dogapi.dog/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val catRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val dogApi: DogApiService by lazy {
        dogRetrofit.create(DogApiService::class.java)
    }

    private val catApi: CatApiService by lazy {
        catRetrofit.create(CatApiService::class.java)
    }

    private val dogRepository: DogFactRepository by lazy {
        DogFactRepositoryImp(dogApi)
    }

    private val catRepository: CatRepository by lazy {
        CatRepositoryImp(catApi)
    }

    private val getDogFactUseCase: GetDogFactUseCase by lazy {
        GetDogFactUseCaseImp(dogRepository)
    }

    private val getCatUseCase: GetCatUseCase by lazy {
        GetCatUseCaseImp(catRepository)
    }

    fun provideDogFactViewModelFactory(): DogFactViewModelFactory {
        return DogFactViewModelFactory(getDogFactUseCase, getCatUseCase)
    }

//    fun provideDogFactViewModelFactory(): DogFactViewModelFactory {
//        return DogFactViewModelFactory(getDogFactUseCase)
//    }
}

