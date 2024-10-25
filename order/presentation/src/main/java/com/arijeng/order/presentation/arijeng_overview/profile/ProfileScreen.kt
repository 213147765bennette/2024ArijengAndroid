package com.arijeng.order.presentation.arijeng_overview.profile

import androidx.compose.runtime.Composable
import com.arijeng.order.presentation.arijeng_overview.ArijengOverviewViewModel
import org.koin.androidx.compose.koinViewModel


/**
 * Created by {Bennette Molepo} on {2024/09/18}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Composable
fun ArijengProfileScreenRoot(
    onAboutClick: () -> Unit,
    onTermsAndConditionsClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onLogoutClick: () -> Unit,
    viewModel: ProfileViewModel = koinViewModel()
) {
    ProfileScreen(
        onAction = { action ->
            when(action){
                ProfileAction.OnAboutClick -> onAboutClick()
                ProfileAction.OnEditProfileClick -> onEditProfileClick()
                ProfileAction.OnLogoutClick -> onLogoutClick()
                ProfileAction.OnTermsAndConditionClick -> onTermsAndConditionsClick()
            }
        }
    )
}

@Composable
fun ProfileScreen(
    onAction: (ProfileAction) -> Unit
) {

}

@Composable
fun ArijengEditProfileScreenRoot(
    onAboutClick: () -> Unit,

    viewModel: ProfileViewModel = koinViewModel()
) {
    EditProfileScreen(
        onAction = { action ->
            when(action){
                EditProfileAction.OnEditProfileClick -> {}
            }
        }
    )
}

@Composable
fun EditProfileScreen(
    onAction: (EditProfileAction) -> Unit
) {

}