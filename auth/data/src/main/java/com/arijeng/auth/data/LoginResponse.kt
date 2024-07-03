package com.arijeng.auth.data

import kotlinx.serialization.Serializable


/**
 * Created by {Bennette Molepo} on {2024/05/21}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Serializable
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpirationTimestamp: Long,
    val userId: String
)
