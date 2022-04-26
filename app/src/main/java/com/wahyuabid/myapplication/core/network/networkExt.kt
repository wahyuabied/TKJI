package com.wahyuabid.myapplication.core.ext

import com.google.gson.Gson
import com.wahyuabid.myapplication.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Wahyu Abid A on 07/04/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
fun initRetrofit(url: String?, gson: Gson, client: OkHttpClient): Retrofit {
    return Retrofit.Builder().apply {
        url?.also { baseUrl(it) }
        client(client)
        addConverterFactory(GsonConverterFactory.create(gson))
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }.build()
}

fun initOkHttpClient(
    isDebug: Boolean,
    interceptors: Set<Interceptor>,
    debugInterceptor: Set<Interceptor>
): OkHttpClient =
    OkHttpClient.Builder().apply {
        interceptors.forEach { addInterceptor(it) }
        if (isDebug) debugInterceptor.forEach { addInterceptor(it) }
        connectTimeout(8, TimeUnit.SECONDS)
        readTimeout(1, TimeUnit.MINUTES)
        writeTimeout(1, TimeUnit.MINUTES)
    }.build()

val HEADER_CONTENT_TYPE = Pair("Content-Type", "application/json")

val HEADER_PLATFORM = Pair("POST-Platform", "android")

val HEADER_AGENT = Pair("User-Agent", "android-${BuildConfig.VERSION_NAME}")

fun authorizationHeader(authToken: String) = Pair("Authorization", "Bearer $authToken")