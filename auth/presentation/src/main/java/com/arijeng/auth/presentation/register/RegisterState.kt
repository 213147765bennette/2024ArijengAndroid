@file:OptIn(ExperimentalFoundationApi::class)

package com.arijeng.auth.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import com.arijeng.auth.domain.PasswordValidationState


/**
 * Created by {Bennette Molepo} on {2024/05/13}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
data class RegisterState(
    val email: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = false,
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState(),
    val isRegistering: Boolean = false,
    val canRegister: Boolean = false
)