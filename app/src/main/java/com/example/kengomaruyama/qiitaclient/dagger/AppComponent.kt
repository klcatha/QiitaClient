package com.example.kengomaruyama.qiitaclient.dagger

import dagger.Component
import com.example.kengomaruyama.qiitaclient.MainActivity
import javax.inject.Singleton

@Component(modules = arrayOf(ClientModule::class))
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}