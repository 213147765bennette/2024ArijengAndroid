package com.arijeng.order.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.arijeng.core.domain.arijeng_overview.dto.ItemDTO


/**
 * Created by {Bennette Molepo} on {2023/05/22}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class SharedViewModel : ViewModel() {

    var productItem by mutableStateOf<ItemDTO?>(null)
        private set

    fun setArijengItem(itemDTO: ItemDTO) {
        productItem = itemDTO
    }
}