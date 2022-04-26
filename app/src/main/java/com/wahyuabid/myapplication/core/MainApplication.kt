package com.wahyuabid.myapplication.core

import androidx.multidex.MultiDexApplication
import com.google.gson.Gson
import com.wahyuabid.myapplication.BuildConfig
import com.wahyuabid.myapplication.MainActivityViewModel
import com.wahyuabid.myapplication.core.ext.initOkHttpClient
import com.wahyuabid.myapplication.core.ext.initRetrofit
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Wahyu Abid A on 14/03/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class MainApplication: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf( module{
                single { Gson() }
                single { initOkHttpClient(BuildConfig.DEBUG,get(named(Const.DEFAULT_INTERCEPTORS)),get(named(Const.DEBUG_INTERCEPTOR))) }
                single(named(Const.KEY_BASE_URL)) { BuildConfig.BASE_URL }
                single { initRetrofit(get(named(Const.KEY_BASE_URL)),get(),get()) }
                viewModel { MainActivityViewModel() }
            }))
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}