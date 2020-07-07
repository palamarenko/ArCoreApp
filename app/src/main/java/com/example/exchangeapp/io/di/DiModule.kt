package com.example.exchangeapp.io.di

import android.content.Context
import androidx.room.Room
import com.example.exchangeapp.App
import com.example.exchangeapp.io.db.DataBase
import com.example.exchangeapp.io.interactors.ItemInteractor
import com.example.exchangeapp.io.interactors.ItemInteractorImp
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
    val itemInteractor: ItemInteractor
}


val appComponent: AppComponent = App.component
val itemInteractor = appComponent.itemInteractor


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
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRateInteractor(db: DataBase, apiGet: ApiGet): ItemInteractor {
        return ItemInteractorImp(db, apiGet)
    }


}