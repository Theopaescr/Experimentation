package br.com.mpc.experimentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.mpc.experimentation.firebase.FirebaseRemoteConfig
import br.com.mpc.experimentation.utils.API_PUBLIC_KEY
import br.com.mpc.experimentation.utils.extensions.openMainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        FirebaseRemoteConfig.setConfigurations()

        runBlocking {
            delay(4000)
        }

        val apiKey = FirebaseRemoteConfig.getString(API_PUBLIC_KEY)

        if (apiKey.isEmpty()) finish()
        else openMainActivity()
    }
}