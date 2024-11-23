@file:OptIn(ExperimentalFoundationApi::class)

package com.arijeng.auth.presentation.otp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState


/**
 * Created by {Bennette Molepo} on {2024/07/08}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
data class OTPState(
    val otpFieldOne: TextFieldState = TextFieldState(),
    val otpFieldTwo: TextFieldState = TextFieldState(),
    val otpFieldThree: TextFieldState = TextFieldState(),
    val otpFieldFour: TextFieldState = TextFieldState(),
    val isOTPConfirming: Boolean = false,
    val canConfirmOTP: Boolean = false
)