package com.arijeng.auth.data

import kotlinx.serialization.Serializable


/**
 * Created by {Bennette Molepo} on {2024/05/19}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Serializable
data class RegisterRequest(
    val email: String,
    val password: String
)
