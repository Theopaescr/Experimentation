package br.com.mpc.experimentation

import android.app.Application
import br.com.mpc.experimentation.di.mainModule
import br.com.mpc.experimentation.firebase.FirebaseRemoteConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

class ExperimentalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val listOfModule = listOf<Module>(mainModule)

        startKoin {
            modules(listOfModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}