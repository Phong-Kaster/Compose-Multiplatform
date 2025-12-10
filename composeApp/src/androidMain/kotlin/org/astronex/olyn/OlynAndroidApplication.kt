package org.astronex.olyn

import android.app.Application

// androidMain
class OlynAndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initAndroidApplicationContext(context = this@OlynAndroidApplication)
    }
}