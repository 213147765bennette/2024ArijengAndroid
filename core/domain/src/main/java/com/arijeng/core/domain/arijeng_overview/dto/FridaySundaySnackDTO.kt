package com.arijeng.order.data.dto

import kotlinx.serialization.Serializable


/**
 * Created by {Bennette Molepo} on {2023/05/09}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Serializable
data class FridaySundaySnackDTO (
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    var image: String,
    var menuCategory: String,
    var menuItemRank: String
)