package com.example.exchangeapp

import android.app.Application
import com.example.exchangeapp.io.di.AppComponent
import com.example.exchangeapp.io.di.DaggerAppComponent
import io.reactivex.plugins.RxJavaPlugins
import ua.palamarenko.cozyandroid2.CozyLibrary
import ua.palamarenko.cozyandroid2.di.AppModule
import java.text.DecimalFormat

class App : Application() {
    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        CozyLibrary.init(this)
        component = buildComponent()
        RxJavaPlugins.setErrorHandler { e ->
            e.printStackTrace()
        }
    }


    private fun buildComponent(): AppComponent {
        return DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

}


fun Double.displayText(): String {
    return DecimalFormat("###.##").format(this)
}
