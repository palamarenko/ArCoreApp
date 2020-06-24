package com.example.exchangeapp.io.di

import android.content.Context
import androidx.room.Room
import com.example.exchangeapp.App
import com.example.exchangeapp.io.db.DataBase
import com.example.exchangeapp.io.interactors.RateInteractor
import com.example.exchangeapp.io.interactors.RateInteractorImp
import com.example.exchangeapp.io.rest.ApiGet
import dagger.Component
import dagger.Module
import dagger.Provides
import ua.palamarenko.cozyandroid2.di.AppModule
import ua.palamarenko.cozyandroid2.rest.ApiFactory
import ua.palamarenko.cozyandroid2.rest.Rest
import javax.inject.Singleton


@Component(modules = [(AppModule::class), (DiModule::class)])
@Singleton
interface AppComponent {

    val rest: ApiGet
    val db: DataBase
    val applicationContext: Context
    val rateInteractor: RateInteractor
}


val appComponent: AppComponent = App.component
val rateInteractor = appComponent.rateInteractor


@Module
class AppModule(private val appContext: Context) {

    @Provides
    internal fun provideContext(): Context {
        return appContext
    }
}


@Module
class DiModule {

    @Provides
    @Singleton
    internal fun provideRest(): ApiGet {
        return Rest(ApiFactory<ApiGet>("https://api.exchangeratesapi.io/", ApiGet::class.java)).api
    }


    @Provides
    @Singleton
    fun provideDb(context: Context): DataBase {
        val cjName = "Db"
        return Room.databaseBuilder(context, DataBase::class.java, cjName)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRateInteractor(db: DataBase, apiGet: ApiGet): RateInteractor {
        return RateInteractorImp(db, apiGet)
    }


}