package com.arijeng.core.domain


/**
 * Created by {Bennette Molepo} on {2024/07/09}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

data class UserInfo(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val status: String,
    val roles: Set<String>
)