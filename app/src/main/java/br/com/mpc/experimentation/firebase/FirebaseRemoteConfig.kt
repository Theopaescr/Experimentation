package br.com.mpc.experimentation.firebase

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

object FirebaseRemoteConfig {

    fun setConfigurations() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    fun getString(key: String) = Firebase.remoteConfig.getString(key)
    fun getBoolean(key: String) = Firebase.remoteConfig.getBoolean(key)
    fun getDouble(key: String) = Firebase.remoteConfig.getDouble(key)
    fun getLong(key: String) = Firebase.remoteConfig.getLong(key)
}