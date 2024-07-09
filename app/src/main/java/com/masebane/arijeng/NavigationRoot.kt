package com.masebane.arijeng

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.arijeng.auth.presentation.intro.IntroScreenRoot
import com.arijeng.auth.presentation.login.LoginScreenRoot
import com.arijeng.auth.presentation.otp.OTPScreenRoot
import com.arijeng.auth.presentation.register.RegisterScreenRoot


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
                                navController.navigate("register")
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
           //HomeOverviewScreenRoot()
        }
    }
}