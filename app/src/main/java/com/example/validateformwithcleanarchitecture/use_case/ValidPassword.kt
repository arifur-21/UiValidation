package com.example.validateformwithcleanarchitecture.use_case

import android.util.Patterns

class ValidPassword {

    fun execute(password: String): ValidationResult{
        if (password.length < 8){
            return ValidationResult(
                successfulll = false,
                errorMessage = "The password needs to consist of at least 8 charactter"
            )
        }
        val containsLetterAndDigits = password.any{ it.isDigit()} && password.any { it.isLetter() }
        if (containsLetterAndDigits){
            return ValidationResult(
                successfulll = false,
                errorMessage = "The password needs to contain at least one letter and digit "
            )
        }
        return ValidationResult(successfulll = true)
    }
}