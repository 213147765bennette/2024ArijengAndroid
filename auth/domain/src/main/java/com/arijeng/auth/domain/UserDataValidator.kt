package com.arijeng.auth.domain

import com.arijeng.auth.domain.PatternValidator


/**
 * Created by {Bennette Molepo} on {2024/05/14}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class UserDataValidator(
    private val patternValidator: PatternValidator
) {

    fun isValidEmail(email: String): Boolean{
        return patternValidator.matches(email.trim())
    }

    fun validatePassword(password: String): PasswordValidationState{
        val hasMinLength = password.length >= MIN_PASSWORD_LENGTH
        val hasDigit = password.any { it.isDigit() }
        val hasLowerCaseCharacter = password.any { it.isLowerCase() }
        val hasUpperCaseCharacter = password.any { it.isUpperCase() }

        return PasswordValidationState(
            hasMinLength = hasMinLength,
            hasNumber = hasDigit,
            hasLowerCaseCharacter = hasLowerCaseCharacter,
            hasUpperCaseCharacter = hasUpperCaseCharacter
        )
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 9
    }
}