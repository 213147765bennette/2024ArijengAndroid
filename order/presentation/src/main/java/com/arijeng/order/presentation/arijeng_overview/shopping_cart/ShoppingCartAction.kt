package com.arijeng.order.presentation.arijeng_overview.shopping_cart


/**
 * Created by {Bennette Molepo} on {2024/09/04}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
sealed interface ShoppingCartAction {
    data object OnRemoveCartItemClick: ShoppingCartAction
    data object OnClearAllClick: ShoppingCartAction
    data object OnCartItemIncrementClick: ShoppingCartAction
    data object OnCartItemDecrementClick: ShoppingCartAction
    data object OnGoToCheckoutClick: ShoppingCartAction
}