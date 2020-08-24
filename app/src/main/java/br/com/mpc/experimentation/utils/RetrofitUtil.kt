package br.com.mpc.experimentation.utils

import android.os.Environment
import br.com.mpc.experimentation.firebase.FirebaseRemoteConfig.getString
import br.com.mpc.experimentation.utils.extensions.md5
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createRetrofitInstance(): Retrofit {
    val gson = GsonBuilder().setLenient().create()

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(MarvelAPIInterceptor())
        .addInterceptor(getLoggingInterceptor())
        .build()

    return Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

class MarvelAPIInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val timestamp = getString(API_TIMESTAMP)
        val publicKey = getString(API_PUBLIC_KEY)
        val privateKey = getString(API_PRIVATE_KEY)

        val hash = md5("$timestamp$privateKey$publicKey")

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("apiKey", publicKey)
            .addQueryParameter("hash", hash)
            .build()

        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}

fun getLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }
}