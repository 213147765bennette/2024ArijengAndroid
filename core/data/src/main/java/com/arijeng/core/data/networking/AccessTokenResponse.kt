package com.arijeng.core.data.networking

import kotlinx.serialization.Serializable


/**
 * Created by {Bennette Molepo} on {2024/05/20}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Serializable
data class AccessTokenResponse(
    val accessToken: String,
    val expirationTimestamp: String
)
