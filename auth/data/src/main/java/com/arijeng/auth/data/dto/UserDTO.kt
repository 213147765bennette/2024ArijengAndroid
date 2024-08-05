package com.arijeng.auth.data.dto


import kotlinx.serialization.Serializable


@Serializable
data class UserDTO(
    var id: String,
    var firstName: String,
    var lastName: String,
    var email: String,
    var phone: String,
    var status: String,
    var roles: Set<String>,
    var codeConfirmationDTO: CodeConfirmationDTO? = null,
    var token: String? = null
)
