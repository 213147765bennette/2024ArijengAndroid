package com.arijeng.auth.presentation.intro


/**
 * Created by {Bennette Molepo} on {2024/05/11}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
sealed interface IntroAction {
    data object OnSignInClick: IntroAction
    data object OnSignUpClick: IntroAction
}