package com.arijeng.auth.domain


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

    fun validateMobileNumber(mobileNumber: String): MobileNumberValidationState{
        val hasMaxLength = mobileNumber.length >= MAX_MOBILE_NUMBER_LENGTH
        return MobileNumberValidationState(hasMaxLength = hasMaxLength)
    }

    fun validateFirstNames(firstName: String): FirstNameValidationState{
        val hasFirstNameMinLength = firstName.length  >= MIN_USERNAME_LENGTH
        return FirstNameValidationState(hasFirstNameMinLength = hasFirstNameMinLength)
    }

    fun validateLastNames(lastName:String): LastNameValidationState{
        val hasLastNameMinLength = lastName.length  >= MIN_USERNAME_LENGTH
        return LastNameValidationState(hasLastNameMinLength = hasLastNameMinLength)
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 9
        const val MAX_MOBILE_NUMBER_LENGTH = 10
        const val MIN_USERNAME_LENGTH = 3
    }
}