package com.mcmouse88.koinscopeapp.core

import android.app.Application
import com.mcmouse88.koinscopeapp.features.post.di.postModule
import com.mcmouse88.koinscopeapp.features.user.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(postModule, userModule)
        }
    }
}