package br.com.mpc.experimentation.utils.extensions

import android.app.Activity
import android.content.Intent
import br.com.mpc.experimentation.MainActivity

fun Activity.openMainActivity() {
    val intent = Intent(this.baseContext, MainActivity::class.java)
    startActivity(intent)
    this.finish()
}