package com.arijeng.order.network

import com.arijeng.core.domain.arijeng_overview.MenuOverViewDatasource
import com.arijeng.core.domain.arijeng_overview.MenuOverViewRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


/**
 * Created by {Bennette Molepo} on {2024/08/15}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

val networkModule = module{
    singleOf(::KtorRemoteMenuOverviewDataSourceImpl).bind<MenuOverViewDatasource>()
    singleOf(::KtorRemoteMenuOverviewImpl).bind<MenuOverViewRepository>()
}