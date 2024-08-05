package com.arijeng.auth.data

import kotlinx.serialization.Serializable


/**
 * Created by {Bennette Molepo} on {2024/07/08}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Serializable
data class VerifyOTPRequest(
    val mobileNumber: String,
    val otpCode: String
)