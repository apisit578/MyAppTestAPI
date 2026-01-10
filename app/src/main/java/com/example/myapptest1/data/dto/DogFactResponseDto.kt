package com.example.myapptest1.data.dto

data class DogFactResponseDto(
    val data: List<DogFactDto>
)

data class DogFactDto(
    val id: String,
    val type: String,
    val attributes: DogFactAttributesDto
)

data class DogFactAttributesDto(
    val body: String
)