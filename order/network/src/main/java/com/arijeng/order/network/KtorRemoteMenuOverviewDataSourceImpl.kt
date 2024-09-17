package com.arijeng.order.network

import com.arijeng.core.data.networking.get
import com.arijeng.core.domain.arijeng_overview.MenuOverViewDatasource
import com.arijeng.core.domain.util.DataError
import com.arijeng.core.domain.util.Result
import com.arijeng.core.domain.arijeng_overview.dto.MenuDTO
import io.ktor.client.HttpClient


/**
 * Created by {Bennette Molepo} on {2024/08/15}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

class KtorRemoteMenuOverviewDataSourceImpl(
    private val httpClient: HttpClient
) : MenuOverViewDatasource {
    override suspend fun getHomeOverviewMenu(): Result<MenuDTO, DataError.Network> {
        return httpClient.get<MenuDTO>(
            route = "menu/all"
        )
    }
}