package com.arijeng.order.presentation.arijeng_overview.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.arijeng.order.presentation.arijeng_overview.ArijengOverviewAction
import com.arijeng.order.presentation.arijeng_overview.ArijengOverviewState


/**
 * Created by {Bennette Molepo} on {2024/09/18}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class ProfileViewModel(

) : ViewModel(){

    var state by mutableStateOf(ProfileState())
        private set

    fun onAction(action: ProfileAction) {
        when (action) {
            ProfileAction.OnAboutClick -> {

            }
            ProfileAction.OnEditProfileClick -> {

            }
            ProfileAction.OnLogoutClick -> {

            }
            ProfileAction.OnTermsAndConditionClick -> {

            }
        }
    }
}