package com.arijeng.core.data.auth

import kotlinx.serialization.Serializable


/**
 * Created by {Bennette Molepo} on {2024/05/20}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Serializable
data class UserInfoSerializable(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val status: String,
    val roles: Set<String>,
    val accessToken: String
)
