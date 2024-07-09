package com.arijeng.auth.presentation.otp

import com.arijeng.auth.presentation.register.RegisterEvent
import com.arijeng.core.presentation.ui.UiText


/**
 * Created by {Bennette Molepo} on {2024/07/08}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
interface OTPEvent {
    data object OTPConfirmationSuccess: OTPEvent
    data class Error(val error: UiText): OTPEvent
}