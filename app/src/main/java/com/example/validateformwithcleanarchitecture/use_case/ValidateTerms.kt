package com.example.validateformwithcleanarchitecture.use_case

import android.util.Patterns

class ValidateTerms {

    fun execute(acceptedTerms: Boolean): ValidationResult{
        if (!acceptedTerms){
            return ValidationResult(
                successfulll = false,
                errorMessage = "Please accept the terms"
            )
        }

        return ValidationResult(successfulll = true)
    }
}