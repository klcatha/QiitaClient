package com.example.kengomaruyama.qiitaclient

import android.app.Application
import com.example.kengomaruyama.qiitaclient.dagger.AppComponent
import com.example.kengomaruyama.qiitaclient.dagger.DaggerAppComponent

class QiitaClientApp : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}