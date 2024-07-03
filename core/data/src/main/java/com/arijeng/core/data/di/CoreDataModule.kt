package com.arijeng.core.data.di


import android.content.SharedPreferences
import com.arijeng.core.data.auth.EncryptedSessionStorage
import com.arijeng.core.data.networking.HttpClientFactory
import com.arijeng.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module




/**
 * Created by {Bennette Molepo} on {2024/06/24}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}