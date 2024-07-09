@file:Suppress("OPT_IN_USAGE_FUTURE_ERROR")
@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.arijeng.auth.presentation.otp

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
import com.arijeng.auth.presentation.register.RegisterEvent
import com.arijeng.core.domain.SessionStorage
import com.arijeng.core.domain.util.DataError
import com.arijeng.core.domain.util.Result
import com.arijeng.core.presentation.ui.UiText
import com.arijeng.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


/**
 * Created by {Bennette Molepo} on {2024/07/08}
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class OTPViewModel(
    private val userDataValidator: UserDataValidator,
    private val authRepository: AuthRepository,
    private val sessionStorage: SessionStorage
) : ViewModel() {
    var state by mutableStateOf(OTPState())
        private set

    private var otpCode: String = ""

    //send the event from viewmodel to the ui
    private val eventChannel = Channel<OTPEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        combine(
            state.otpFieldOne.textAsFlow(),
            state.otpFieldTwo.textAsFlow(),
            state.otpFieldThree.textAsFlow(),
            state.otpFieldFour.textAsFlow()
        ) { fieldOne, fieldTwo, fieldThree, fieldFour ->
            state = state.copy(
                canConfirmOTP = fieldOne.isNotEmpty() && fieldTwo.isNotEmpty() && fieldThree.isNotEmpty() && fieldFour.isNotEmpty()
            )
        }.launchIn(viewModelScope)

    }

    //from UI to viewmodel
    fun onAction(action: OTPAction) {
        when (action) {
            OTPAction.OnConfirmOTPClick -> confirmOTP()
            else -> Unit
        }
    }

    private fun confirmOTP() {
        viewModelScope.launch {
            state = state.copy(isOTPConfirming = true)
            otpCode =
                state.otpFieldOne.text.toString() + state.otpFieldTwo.text.toString() + state.otpFieldThree.text.toString() + state.otpFieldFour.text.toString()
            val result = authRepository.verifyOTP(
                mobileNumber = sessionStorage.getUserInfo()?.phone ?: "",
                otpCode = otpCode
            )
            state = state.copy(isOTPConfirming = false)
            when (result) {
                is Result.Error -> {
                    if (result.error == DataError.Network.UNAUTHORIZED) {
                        eventChannel.send(
                            OTPEvent.Error(
                                UiText.StringResource(R.string.error_wrong_otp)
                            )
                        )
                    } else {
                        eventChannel.send(OTPEvent.Error(result.error.asUiText()))
                    }
                }

                is Result.Success -> {
                    eventChannel.send(OTPEvent.OTPConfirmationSuccess)
                }
            }
        }
    }
}