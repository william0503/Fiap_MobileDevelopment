package br.com.fiap.pokedex

import android.app.Application
import com.facebook.stetho.Stetho

class App : Application(){
    override fun onCreate() {
        if(BuildConfig.DEBUG)
            super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}