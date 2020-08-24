package br.com.mpc.experimentation.utils.extensions

import java.security.MessageDigest

fun md5(seed: String): String {
    val bytes = MessageDigest.getInstance("MD5")
        .digest(seed.toByteArray())
    return bytes.toHex()
}

fun ByteArray.toHex(): String {
    return joinToString("") {
        "%02x".format(it)
    }
}