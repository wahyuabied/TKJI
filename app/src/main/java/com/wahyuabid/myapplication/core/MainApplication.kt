package com.wahyuabid.myapplication.core

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.google.gson.Gson
import com.wahyuabid.myapplication.BuildConfig
import com.wahyuabid.myapplication.home.ClassActivityViewModel
import com.wahyuabid.myapplication.core.ext.initOkHttpClient
import com.wahyuabid.myapplication.core.ext.initRetrofit
import com.wahyuabid.myapplication.detail_home.DetailClassActivityViewModel
import com.wahyuabid.myapplication.question.QuestionViewModel
import com.wahyuabid.myapplication.shared_preferences.ClassSharedPref
import org.koin.android.ext.koin.androidApplication
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
class MainApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(module {
                single { Gson() }
                single {
                    initOkHttpClient(
                        BuildConfig.DEBUG,
                        get(named(Const.DEFAULT_INTERCEPTORS)),
                        get(named(Const.DEBUG_INTERCEPTOR))
                    )
                }
                single(named(Const.KEY_BASE_URL)) { BuildConfig.BASE_URL }
                single { initRetrofit(get(named(Const.KEY_BASE_URL)), get(), get()) }
                factory {
                    androidApplication().applicationContext.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
                }
                factory { ClassSharedPref(get()) }
                viewModel { ClassActivityViewModel(get()) }
                viewModel { DetailClassActivityViewModel(get()) }
                viewModel { QuestionViewModel(get()) }
            }))
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}