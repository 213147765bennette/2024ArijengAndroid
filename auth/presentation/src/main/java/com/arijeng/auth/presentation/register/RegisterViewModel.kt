@file:Suppress("OPT_IN_USAGE_FUTURE_ERROR")
@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package com.arijeng.auth.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arijeng.auth.domain.AuthRepository
import com.arijeng.auth.domain.UserDataValidator
import com.arijeng.auth.presentation.R
import com.arijeng.core.domain.util.DataError
import com.arijeng.core.domain.util.Result
import com.arijeng.core.presentation.ui.UiText
import com.arijeng.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


/**
 * Created by {Bennette Molepo} on {2024/05/13}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val authRepository: AuthRepository
): ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    //send the event from viewmodel to the ui
    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        state.firstName.textAsFlow()
            .onEach { firstName ->
                val firstNameValidationState = userDataValidator.validateFirstNames(firstName.toString())
                state = state.copy(
                    firstNameValidationState = firstNameValidationState,
                    canRegister = canRegisterNow()
                    //firstNameValidationState.hasFirstNameMinLength && state.passwordValidationState.isValidPassword && !state.isRegistering
                )
            }
            .launchIn(viewModelScope)

        state.lastName.textAsFlow()
            .onEach { lastName ->
                val lastNameValidationState = userDataValidator.validateLastNames(lastName.toString())
                state = state.copy(
                    lastNameValidationState = lastNameValidationState,
                    canRegister = canRegisterNow()
                )
            }
            .launchIn(viewModelScope)

        state.mobileNumber.textAsFlow()
            .onEach { mobileNumber ->
                val mobileNumberValidationState = userDataValidator.validateMobileNumber(mobileNumber.toString())
                state = state.copy(
                    mobileNumberValidationState = mobileNumberValidationState,
                    canRegister = canRegisterNow()
                )
            }
            .launchIn(viewModelScope)
        state.email.textAsFlow()
            .onEach { email ->
                val isValidEmail = userDataValidator.isValidEmail(email.toString())
                state = state.copy(
                    isEmailValid = isValidEmail,
                    canRegister = canRegisterNow()
                )
            }
            .launchIn(viewModelScope)

        state.password.textAsFlow()
            .onEach { password ->
                val passwordValidationState = userDataValidator.validatePassword(password.toString())
                state = state.copy(
                    passwordValidationState =  passwordValidationState,
                    canRegister = canRegisterNow()
                )
            }
            .launchIn(viewModelScope)
    }

    private fun canRegisterNow() =
        state.firstNameValidationState.hasFirstNameMinLength && state.lastNameValidationState.hasLastNameMinLength
                && state.mobileNumberValidationState.isValidMobileNumber && state.isEmailValid
                && state.passwordValidationState.isValidPassword && !state.isRegistering

    //from UI to viewmodel
    fun onAction(action: RegisterAction){
        when(action){
            RegisterAction.OnRegisterClick -> register()
            RegisterAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }
            else -> Unit
        }
    }

    private fun register(){
        viewModelScope.launch {
            state = state.copy(isRegistering = true)
            val result = authRepository.register(
                firstName = state.firstName.text.toString(),
                lastName = state.lastName.text.toString(),
                email = state.email.text.toString().trim(),
                phone = state.mobileNumber.text.toString(),
                password = state.password.text.toString()
            )
            state = state.copy(isRegistering = false)

            when(result){
                is Result.Error -> {
                    if (result.error == DataError.Network.CONFLICT){
                        eventChannel.send(RegisterEvent.Error(
                            UiText.StringResource(R.string.error_email_exists)
                        ))
                    }else{
                        eventChannel.send(RegisterEvent.Error(result.error.asUiText()))
                    }
                }
                is Result.Success -> {
                    eventChannel.send(RegisterEvent.RegistrationSuccess)
                }
            }
        }
    }
}
