package com.example.exchangeapp.ui.single

import androidx.lifecycle.MutableLiveData
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.exchangeapp.io.di.rateInteractor
import com.example.exchangeapp.ui.base.BaseViewModel
import com.example.exchangeapp.ui.main.BASE_CURRENCY

class SingleChartViewModel : BaseViewModel() {


    fun loadChartData(currency : String) : MutableLiveData<out List<ValueDataEntry>>{
       return rateInteractor.getHistory(BASE_CURRENCY,currency).showProgress().toLiveData()
    }

}