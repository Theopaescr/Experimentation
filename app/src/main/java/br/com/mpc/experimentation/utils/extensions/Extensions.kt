package br.com.mpc.experimentation.utils.extensions

import java.security.MessageDigest

fun md5(seed: String): String {
    val digest = MessageDigest.getInstance("MD5")

    digest.update(seed.toByte())
    return digest.digest().toString()
}