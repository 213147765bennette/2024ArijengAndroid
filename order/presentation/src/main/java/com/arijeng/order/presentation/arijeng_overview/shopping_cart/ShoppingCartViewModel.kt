package com.arijeng.order.presentation.arijeng_overview.shopping_cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


/**
 * Created by {Bennette Molepo} on {2024/09/05}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class ShoppingCartViewModel(

):ViewModel() {
    var state by mutableStateOf(ShoppingCartState())
        private set
}