package com.arijeng.core.domain.arijeng_overview.dto

import kotlinx.serialization.Serializable


/**
 * Created by {Bennette Molepo} on {2023/05/09}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Serializable
data class IceCreamDTO(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    var image: String,
    var menuCategory: String,
    var menuItemRank: String
)
fun IceCreamDTO.toItemDTO(): ItemDTO {
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