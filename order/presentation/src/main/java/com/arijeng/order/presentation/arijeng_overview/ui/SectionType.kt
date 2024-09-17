package com.arijeng.order.presentation.arijeng_overview.ui

import androidx.annotation.IntDef


/**
 * Created by {Bennette Molepo} on {2023/06/05}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Retention(AnnotationRetention.SOURCE)
@IntDef(SectionType.KOTA)
annotation class SectionType{
    companion object {
        const val KOTA = 1
        const val CHIPS = 2
        const val ICE_CREAM = 3
        const val SALADS = 4
        const val SANDWICH = 5
        const val DRINKS = 6
    }
}
