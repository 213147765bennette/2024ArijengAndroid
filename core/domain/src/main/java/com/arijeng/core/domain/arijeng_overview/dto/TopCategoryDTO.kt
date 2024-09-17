package com.arijeng.core.domain.arijeng_overview.dto

import kotlinx.serialization.Serializable


/**
 * Created by {Bennette Molepo} on {2024/08/10}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

@Serializable
data class TopCategoryDTO(
    val id: String,
    val itemPictureUrl: String?,
    val itemName: String,
)
