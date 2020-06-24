package com.example.exchangeapp.io.interactors

import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.exchangeapp.io.db.DataBase
import com.example.exchangeapp.io.db.RateModel
import com.example.exchangeapp.io.rest.ApiGet
import io.reactivex.Single
import ua.palamarenko.cozyandroid2.tools.PreferencesProvider
import ua.palamarenko.cozyandroid2.tools.formatDate
import ua.palamarenko.cozyandroid2.tools.setSchedulers
import java.util.*
import kotlin.collections.ArrayList


const val LAST_LOAD = "LAST_LOAD"
const val DELAY = 1000 * 60 * 10

interface RateInteractor {
    fun getRates(currency: String, forceReload: Boolean = false): Single<out List<RateModel>>
    fun getHistory(currency: String, symbols : String): Single<out List<ValueDataEntry>>
}

class RateInteractorImp(val db: DataBase, val rest: ApiGet) : RateInteractor {

    override fun getRates(currency: String, forceReload: Boolean): Single<out List<RateModel>> {
        if (Date().time - PreferencesProvider.readLong(LAST_LOAD, 0) > DELAY || forceReload) {
            return rest.getLatestCurrency(currency)
                .map {
                    val list = ArrayList<RateModel>()
                    for ((key, value) in it.rates) {
                        list.add(RateModel(key, value))
                    }
                    return@map list
                }.doOnSuccess {
                    db.userDao.updateAndDelete(it)
                    PreferencesProvider.putLong(LAST_LOAD, Date().time)
                }.setSchedulers()
        } else {
            return db.userDao.getList().setSchedulers()
        }
    }

    override fun getHistory(currency: String, symbols: String): Single<out List<ValueDataEntry>> {
        val calendar = Calendar.getInstance()

        val endDate = calendar.time.formatDate("YYYY-MM-dd",false)
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        val startDate = calendar.time.formatDate("YYYY-MM-dd",false)



        return rest.getHistory(startDate,endDate,currency,symbols).map {
            val list = ArrayList<ValueDataEntry>()
            for ((key, value) in it.rates) {
                for ((_, rate) in value) {
                    list.add(ValueDataEntry(key,rate))
                }
            }


            list
        }.setSchedulers()
    }


}