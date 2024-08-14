package com.arijeng.order.presentation.arijeng_overview


/**
 * Created by {Bennette Molepo} on {2024/08/09}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
sealed interface ArijengOverviewAction {
    data object OnViewActiveOrderClick: ArijengOverviewAction
    data object OnLogoutClick: ArijengOverviewAction

    data object OnCartClick: ArijengOverviewAction
}