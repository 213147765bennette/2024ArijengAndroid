package com.arijeng.auth.presentation.login


/**
 * Created by {Bennette Molepo} on {2024/05/20}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
sealed interface LoginAction {
    data object OnTogglePasswordVisibility: LoginAction
    data object OnLoginClick: LoginAction
    data object OnRegisterClick: LoginAction
}