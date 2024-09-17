package com.arijeng.core.domain.arijeng_overview.dto

import kotlinx.serialization.Serializable

@Serializable
data class ItemDTO(
    val id: String,
    val itemName: String,
    val itemDescription: String,
    val itemPrice: Double,
    var imageUrl: String,
    var menuCategory: String,
    var menuItemRank: String
)
