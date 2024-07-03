package com.arijeng.auth.presentation.login

import com.arijeng.core.presentation.ui.UiText


/**
 * Created by {Bennette Molepo} on {2024/05/20}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
sealed interface LoginEvent {
    data class Error(val error:UiText): LoginEvent
    data object LoginSuccess: LoginEvent
}