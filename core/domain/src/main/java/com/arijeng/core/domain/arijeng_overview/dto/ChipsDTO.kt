package com.arijeng.core.domain.arijeng_overview.dto

import kotlinx.serialization.Serializable

@Serializable
data class ChipsDTO(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    var image: String,
    var menuCategory: String,
    var menuItemRank: String
)
fun ChipsDTO.toItemDTO(): ItemDTO {
    return ItemDTO(
        id = id,
        itemName = name,
        itemDescription = description,
        itemPrice = price,
        imageUrl = image,
        menuCategory = menuCategory,
        menuItemRank = menuItemRank
    )
}