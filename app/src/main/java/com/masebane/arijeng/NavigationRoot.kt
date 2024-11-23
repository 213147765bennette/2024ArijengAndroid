package com.masebane.arijeng

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.arijeng.auth.presentation.intro.IntroScreenRoot
import com.arijeng.auth.presentation.login.LoginScreenRoot
import com.arijeng.auth.presentation.otp.OTPScreenRoot
import com.arijeng.auth.presentation.register.RegisterScreenRoot
import com.arijeng.order.presentation.arijeng_overview.ArijengOverviewScreenRoot
import com.arijeng.order.presentation.arijeng_overview.profile.ArijengProfileScreenRoot
import com.arijeng.order.presentation.arijeng_overview.profile.about.ArijengAboutScreenRoot
import com.arijeng.order.presentation.arijeng_overview.profile.termsandconditions.ArijengTermsAndConditionsScreenRoot
import com.arijeng.order.presentation.arijeng_overview.shopping_cart.ShoppingCartScreenRoot
import com.arijeng.order.presentation.more_item_details.MoreItemDetailsScreenRoot
import com.arijeng.order.presentation.viewmodel.SharedViewModel


/**
 * Created by {Bennette Molepo} on {2024/05/15}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "home" else "auth"
    ) {
        authGraph(navController)
        homeGraph(navController)
        moreCartDetailsNavGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController){
    navigation(
        startDestination = "intro",
        route = "auth"
    ){
        composable(route = "intro"){
            IntroScreenRoot(
                onSignUpClick = {
                    //navController.navigate("register")
                    //navController.navigate("otp")
                    //This one is for testing
                    navController.navigate("home_overview")

                },
                onSignInClick = {
                    navController.navigate("login")
                }
            )
        }
        composable(route = "register"){
            RegisterScreenRoot(
                onSignInClick = {
                    navController.navigate("login"){
                        popUpTo("register"){
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {
                    navController.navigate("otp")
                }
            )
        }
        composable("otp"){
            OTPScreenRoot(
                onSuccessfulOTPConfirmation = {
                    navController.navigate("login")
                }
            )
        }
        composable("login"){
           LoginScreenRoot(
               onLoginSuccess = {
                   navController.navigate("home"){
                       popUpTo("auth"){
                           inclusive = true
                       }
                   }
               },
               onSignUpClick = {
                   navController.navigate("register"){
                       popUpTo("login"){
                           inclusive = true
                           saveState = true
                       }
                       restoreState = true
                   }
               }
           )
        }
    }
}

private fun NavGraphBuilder.homeGraph(navController: NavHostController){
    navigation(
        startDestination = "home_overview",
        route = "home"
    ){
        composable("home_overview"){
           ArijengOverviewScreenRoot(
               onViewActiveOrder = {
                    navController.navigate("active_order")
               },
               onCardItemClick = {
                   navController.navigate("more_home_details")
               }
           )
        }

        composable("more_home_details") {
            MoreItemDetailsScreenRoot(
                navController,
                onViewActiveOrder = {
                    navController.navigate("active_order")
                }
            )
        }

        /*  composable(route = MoreHomeItemScreen.AddressDetails.route) {
              AddressBookScreen(navController)
          }*/

        composable("active_order"){
            ShoppingCartScreenRoot(
                navController = navController,
                onCheckOutClick = {

                }
            )
        }
    }
}

private fun NavGraphBuilder.profileGraph(navController: NavHostController){
    navigation(
        startDestination = "profile_overview",
        route = "profile"
    ){
        composable("profile_overview"){
            ArijengProfileScreenRoot(
                onAboutClick = {
                    navController.navigate("about")
                },
                onTermsAndConditionsClick = {
                    navController.navigate("terms_conditions")
                },
                onEditProfileClick = {
                    navController.navigate("edit_profile")
                },
                onLogoutClick = {
                    navController.navigate("logout")
                })
        }

        composable("about") {
            ArijengAboutScreenRoot()
        }

         composable("terms_conditions") {
              ArijengTermsAndConditionsScreenRoot()
          }

        composable("edit_profile"){

        }
        composable("logout"){

        }
    }
}

fun NavGraphBuilder.moreCartDetailsNavGraph(navController: NavHostController) {
    navigation(
        route = "more_cart_graph",
        startDestination = "home_overview"
    ) {
        composable(route = MoreCartScreen.CartDetails.route) {
            //ShoppingCartScreen(navController)
        }

        composable(route = MoreCartScreen.CheckOut.route) {
            ///CheckOutScreen(navController)
        }
    }
}

sealed class MoreCartScreen(val route: String) {
    data object CartDetails : MoreCartScreen(route = "CART")
    data object CheckOut : MoreCartScreen(route = "CHECKOUT")
}