package com.arijeng.order.network

import com.arijeng.core.domain.arijeng_overview.MenuOverViewDatasource
import com.arijeng.core.domain.arijeng_overview.MenuOverViewRepository
import com.arijeng.core.domain.util.DataError
import com.arijeng.core.domain.util.Result
import com.arijeng.core.domain.arijeng_overview.dto.MenuDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by {Bennette Molepo} on {2024/08/15}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class KtorRemoteMenuOverviewImpl(
    private val menuOverViewDatasource: MenuOverViewDatasource
): MenuOverViewRepository{
    override fun getHomeOverviewMenu(): Flow<Result<MenuDTO, DataError.Network>>  = flow{
        try {
            val menuResponse = menuOverViewDatasource.getHomeOverviewMenu()
            if (menuResponse is Result.Success) {
                emit(Result.Success(menuResponse).data)
            }

        }catch (e : Exception){
            emit(Result.Error(DataError.Network.SERVER_ERROR))
        }
    }

}