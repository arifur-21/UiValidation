package com.example.validateformwithcleanarchitecture.use_case

import android.util.Patterns

class ValidateEmail {

    fun execute(email: String): ValidationResult{
        if (email.isBlank()){
        return ValidationResult(
            successfulll = false,
            errorMessage = "the email can't be blank"
        )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidationResult(
                successfulll = false,
                errorMessage = "that's not a valid email"
            )
        }
        return ValidationResult(successfulll = true)
    }


}