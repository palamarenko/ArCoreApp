package com.example.exchangeapp.io.interactors

import com.example.exchangeapp.io.db.DataBase
import com.example.exchangeapp.io.db.ItemModel
import com.example.exchangeapp.io.db.ItemModelDao
import com.example.exchangeapp.io.rest.ApiGet
import io.reactivex.Flowable
import io.reactivex.Single
import ua.palamarenko.cozyandroid2.tools.PreferencesProvider
import ua.palamarenko.cozyandroid2.tools.formatDate
import ua.palamarenko.cozyandroid2.tools.setSchedulers
import ua.palamarenko.cozyandroid2.tools.toSingle
import java.util.*
import kotlin.collections.ArrayList


const val LAST_LOAD = "LAST_LOAD"
const val DELAY = 1000 * 60 * 10

interface ItemInteractor {
    fun addToLibrary(name: String, place : String): Single<ItemModel>
    fun updateToLibrary(id : String, name: String): Single<ItemModel>
    fun listenList() : Flowable<List<ItemModel>>
    fun getList() : Single<List<ItemModel>>
}

class ItemInteractorImp(val db: DataBase, val rest: ApiGet) : ItemInteractor {

    override fun addToLibrary(name: String, place: String): Single<ItemModel> {
       return ItemModel(UUID.randomUUID().toString(),name,place).toSingle()
            .doOnSuccess {
                db.itemDao.insert(it)
            }.setSchedulers()
    }

    override fun updateToLibrary(id: String, name: String): Single<ItemModel> {
       return db.itemDao.get(id).doOnSuccess {
            it.name = name
            db.itemDao.insert(it)
        }.setSchedulers()
    }

    override fun listenList(): Flowable<List<ItemModel>> {
        return db.itemDao.listenList().setSchedulers()
    }

    override fun getList(): Single<List<ItemModel>> {
        return db.itemDao.getList().setSchedulers()
    }


}