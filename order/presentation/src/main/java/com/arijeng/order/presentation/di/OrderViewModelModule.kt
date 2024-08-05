package com.arijeng.order.presentation.di

import com.arijeng.order.presentation.order_overview.OrderOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
/**
 * Created by {Bennette Molepo} on {2024/07/11}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
val orderViewModelModule = module {
    viewModelOf(::OrderOverviewViewModel)
}