package com.arijeng.order.presentation.arijeng_overview.profile

import com.arijeng.order.presentation.arijeng_overview.ArijengOverviewAction


/**
 * Created by {Bennette Molepo} on {2024/09/18}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
sealed interface ProfileAction {
    data object OnAboutClick: ProfileAction
    data object OnTermsAndConditionClick: ProfileAction
    data object OnEditProfileClick: ProfileAction
    data object OnLogoutClick: ProfileAction
}