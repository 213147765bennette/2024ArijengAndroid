package com.arijeng.auth.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CodeConfirmationDTO(var code: String)