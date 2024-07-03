@file:OptIn(ExperimentalFoundationApi::class)

package com.arijeng.auth.presentation.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState


/**
 * Created by {Bennette Molepo} on {2024/05/20}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
data class LoginState(
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val canLogin: Boolean = false,
    val isLoggingIn: Boolean = false
)
