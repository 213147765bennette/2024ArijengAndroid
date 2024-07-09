package com.arijeng.auth.presentation.di

import com.arijeng.auth.presentation.login.LoginViewModel
import com.arijeng.auth.presentation.otp.OTPViewModel
import com.arijeng.auth.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


/**
 * Created by {Bennette Molepo} on {2024/05/15}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::OTPViewModel)
}