package com.arijeng.order.presentation.arijeng_overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState


/**
 * Created by {Bennette Molepo} on {2024/08/09}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@OptIn(ExperimentalFoundationApi::class)
data class ArijengOverviewState(
    val mealName: TextFieldState = TextFieldState()
)
