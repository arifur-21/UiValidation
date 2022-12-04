package com.example.validateformwithcleanarchitecture.use_case

import android.util.Patterns

class ValidateRepeatedPassword {

    fun execute(password: String, repeatedPassword: String): ValidationResult{

            if (password != repeatedPassword) {
                return ValidationResult(
                    successfulll = false,
                    errorMessage = "The Password don't match"
                )
            }

            return ValidationResult(successfulll = true)

        }
    }
