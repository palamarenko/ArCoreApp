package com.example.exchangeapp.ui.main

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.exchangeapp.io.di.rateInteractor
import com.example.exchangeapp.ui.base.BaseViewModel
import com.example.exchangeapp.ui.single.CURRENCY_KEY
import com.example.exchangeapp.ui.single.SingleChartFragment
import ua.palamarenko.cozyandroid2.CozyCell
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.NAVIGATE
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.NavigateNewActivity

class MainViewModel : BaseViewModel() {



    fun getRates(currency : String, forceReload : Boolean = false) : MutableLiveData<List<RateCell>>{
        return rateInteractor.getRates(currency,forceReload).map {
            it.map { RateCell(it,this) }
        }.showProgress().toLiveData()
    }

    fun navigate(currency : String){
        task(NAVIGATE,NavigateNewActivity(SingleChartFragment::class.java), Bundle().apply {
            putString(CURRENCY_KEY,currency)
        })
    }


}