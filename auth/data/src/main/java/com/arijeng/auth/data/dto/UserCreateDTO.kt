package com.arijeng.auth.data.dto

import kotlinx.serialization.Serializable


/**
 * Created by {Bennette Molepo} on {2024/05/19}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Serializable
data class UserCreateDTO(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val pwd: CharArray
)
