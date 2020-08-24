package br.com.mpc.experimentation.firebase

import android.content.Context
import br.com.mpc.experimentation.di.internetModule
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules

object FirebaseRemoteConfig {

    fun refresh(onFinish: (isSuccess: Boolean) -> Unit) {

        Firebase.remoteConfig.fetchAndActivate()
            .addOnCompleteListener {
                onComplete(it, onFinish)
            }.addOnFailureListener {
                it.printStackTrace()
            }
    }

    fun configure(ctx: Context): FirebaseRemoteConfig {
        Firebase.initialize(ctx)

        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }

        remoteConfig.setConfigSettingsAsync(configSettings)

        return remoteConfig
    }

    private fun onComplete(
        it: Task<Boolean>,
        onFinished: (success: Boolean) -> Unit
    ) {
        if (GlobalContext().getOrNull() == null) {
            loadKoinModules(internetModule)
        }
        onFinished.invoke(it.isSuccessful)
    }

    fun getString(key: String) = Firebase.remoteConfig.getString(key)
    fun getBoolean(key: String) = Firebase.remoteConfig.getBoolean(key)
    fun getDouble(key: String) = Firebase.remoteConfig.getDouble(key)
    fun getLong(key: String) = Firebase.remoteConfig.getLong(key)
}

