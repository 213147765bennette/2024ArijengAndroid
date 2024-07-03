package com.arijeng.core.domain


/**
 * Created by {Bennette Molepo} on {2024/06/24}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
data class AuthInfo (
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)