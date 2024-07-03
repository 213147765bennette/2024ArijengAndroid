package com.arijeng.auth.data

import kotlinx.serialization.Serializable


/**
 * Created by {Bennette Molepo} on {2024/05/21}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)
