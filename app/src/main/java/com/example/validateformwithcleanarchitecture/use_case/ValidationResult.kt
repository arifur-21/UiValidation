package com.example.validateformwithcleanarchitecture.use_case

data class ValidationResult(
    val successfulll: Boolean,
    val errorMessage: String? = null
)
