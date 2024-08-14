package com.arijeng.order.presentation.arijeng_overview.model


/**
 * Created by {Bennette Molepo} on {2024/08/13}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
data class FavouriteMealsUi(
    val id: String,
    val itemPictureUrl: String? = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/sandwich%2Fsand_five.png?alt=media&token=7ee8444e-69bd-4b43-a2a0-5fb337f4d07e",
    val itemName: String,
    val itemDescription: String,
    val itemPrice: String,
    val itemSpecialPercentage: String,
)