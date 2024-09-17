package com.arijeng.order.presentation.arijeng_overview.shopping_cart


/**
 * Created by {Bennette Molepo} on {2024/09/05}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
data class ShoppingCartState(
    val canCheckOut: Boolean = false,
    val isCheckingOut: Boolean = false
)