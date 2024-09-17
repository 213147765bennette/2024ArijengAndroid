package com.arijeng.order.presentation.arijeng_overview.ui

import androidx.annotation.StringRes


/**
 * Created by {Bennette Molepo} on {2023/06/11}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
data class ItemSection (
    @SectionType val type: Int,
    @StringRes val name : Int,
)