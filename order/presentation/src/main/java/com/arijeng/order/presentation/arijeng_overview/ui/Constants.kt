package com.arijeng.order.presentation.arijeng_overview.ui

import com.arijeng.order.presentation.R


/**
 * Created by {Bennette Molepo} on {2023/06/12}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
val defaultSectionList by lazy {
    listOf(
        ItemSection(name = R.string.label_kota, type = SectionType.KOTA),
        ItemSection(name = R.string.label_chips, type = SectionType.CHIPS),
        ItemSection(name = R.string.label_ice_cream, type = SectionType.ICE_CREAM),
        ItemSection(name = R.string.label_salads, type = SectionType.SALADS),
        ItemSection(name = R.string.label_sandwich, type = SectionType.SANDWICH),
        ItemSection(name = R.string.label_drinks, type = SectionType.DRINKS)
    )
}
