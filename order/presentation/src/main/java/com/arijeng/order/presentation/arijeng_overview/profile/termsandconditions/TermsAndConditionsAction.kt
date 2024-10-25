package com.arijeng.order.presentation.arijeng_overview.profile.termsandconditions


/**
 * Created by {Bennette Molepo} on {2024/09/18}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
sealed interface TermsAndConditionsAction {
    data object OnAboutClick: TermsAndConditionsAction
}