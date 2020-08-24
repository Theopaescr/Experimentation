package br.com.mpc.experimentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.mpc.experimentation.firebase.FirebaseRemoteConfig
import br.com.mpc.experimentation.utils.API_PUBLIC_KEY
import br.com.mpc.experimentation.utils.extensions.openMainActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlin.coroutines.CoroutineContext

class SplashActivity : AppCompatActivity(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext get() = job + Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        FirebaseRemoteConfig.refresh { isSuccess ->
            if (isSuccess) openMainActivity()
            else finishApp()
        }
    }

    private fun finishApp() {
        launch {
            delay(4000)
            withContext(Main){ finishApp() }
        }
    }
}