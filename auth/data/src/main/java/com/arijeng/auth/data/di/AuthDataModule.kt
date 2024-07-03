package com.arijeng.auth.data.di

import com.arijeng.auth.data.AuthRepositoryImpl
import com.arijeng.auth.data.EmailPatternValidator
import com.arijeng.auth.domain.AuthRepository
import com.arijeng.auth.domain.PatternValidator
import com.arijeng.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


/**
 * Created by {Bennette Molepo} on {2024/05/15}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}