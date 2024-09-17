package com.arijeng.core.domain.arijeng_overview

import com.arijeng.core.domain.util.DataError
import com.arijeng.core.domain.util.Result
import com.arijeng.core.domain.arijeng_overview.dto.MenuDTO


/**
 * Created by {Bennette Molepo} on {2024/08/15}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
interface MenuOverViewDatasource {
    suspend fun getHomeOverviewMenu(): Result<MenuDTO, DataError.Network>
}