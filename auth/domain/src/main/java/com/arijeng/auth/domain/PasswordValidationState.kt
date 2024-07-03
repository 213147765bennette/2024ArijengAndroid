package com.arijeng.auth.domain


/**
 * Created by {Bennette Molepo} on {2024/05/13}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
data class PasswordValidationState(
    val hasMinLength: Boolean = false,
    val hasNumber: Boolean = false,
    val hasLowerCaseCharacter: Boolean = false,
    val hasUpperCaseCharacter: Boolean = false
){
    val isValidPassword: Boolean
        get() = hasMinLength && hasNumber && hasLowerCaseCharacter && hasUpperCaseCharacter
}
