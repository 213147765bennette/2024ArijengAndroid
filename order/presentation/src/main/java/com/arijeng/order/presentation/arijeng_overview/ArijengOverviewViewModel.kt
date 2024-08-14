@file:Suppress("OPT_IN_USAGE_FUTURE_ERROR")

package com.arijeng.order.presentation.arijeng_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


/**
 * Created by {Bennette Molepo} on {2024/08/09}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class ArijengOverviewViewModel(

) : ViewModel() {

    var state by mutableStateOf(ArijengOverviewState())
        private set

    fun onAction(action: ArijengOverviewAction) {
        when (action) {
            ArijengOverviewAction.OnCartClick -> {

            }
            ArijengOverviewAction.OnLogoutClick -> {

            }
            ArijengOverviewAction.OnViewActiveOrderClick -> {

            }
        }
    }

}