package com.example.validateformwithcleanarchitecture.preseantaion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.validateformwithcleanarchitecture.use_case.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validPassword: ValidPassword = ValidPassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
    private val validateTerms: ValidateTerms = ValidateTerms()
): ViewModel() {

    var state by mutableStateOf(RegistrationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent){
        when(event){
            is RegistrationFormEvent.EmailChanged ->{
                state = state.copy(email = event.email)
            }
            is RegistrationFormEvent.PasswordChanged ->{
                state = state.copy(password = event.password)
            }
            is RegistrationFormEvent.RepeatedPasswordChanged ->{
                state = state.copy(repeatePassword = event.repeatedPassword)
            }
            is RegistrationFormEvent.AcceptTerms ->{
                state = state.copy(acceptedTerms = event.isAccepted)
            }
            is RegistrationFormEvent.submit ->{
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validPassword.execute(state.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(state.password, state.repeatePassword)
        val termsResult = validateTerms.execute(state.acceptedTerms)
        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termsResult
        ).any{it.successfulll}

        if (hasError){
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent{
        object Success: ValidationEvent()
    }
}

