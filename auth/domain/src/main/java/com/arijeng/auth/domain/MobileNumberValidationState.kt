package com.arijeng.auth.domain


/**
 * Created by {Bennette Molepo} on {2024/05/13}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
data class MobileNumberValidationState(
    val hasMaxLength: Boolean = false
){
    val isValidMobileNumber: Boolean
        get() = hasMaxLength
}
