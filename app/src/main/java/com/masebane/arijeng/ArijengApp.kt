package com.masebane.arijeng

import android.app.Application
import com.arijeng.auth.data.di.authDataModule
import com.arijeng.auth.presentation.BuildConfig
import com.arijeng.auth.presentation.di.authViewModelModule
import com.arijeng.core.data.di.coreDataModule
import com.arijeng.order.network.networkModule
import com.arijeng.order.presentation.di.orderViewModelModule
import com.masebane.arijeng.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber


/**
 * Created by {Bennette Molepo} on {2024/05/15}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class ArijengApp: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@ArijengApp)
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
                orderViewModelModule,
                networkModule
            )
        }
    }
}