package com.arijeng.auth.presentation.register

import com.arijeng.core.presentation.ui.UiText


/**
 * Created by {Bennette Molepo} on {2024/05/13}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

sealed interface RegisterEvent{
    data object RegistrationSuccess: RegisterEvent
    data class Error(val error: UiText): RegisterEvent

}