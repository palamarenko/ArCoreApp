package com.example.exchangeapp.ui.library

import androidx.lifecycle.MutableLiveData
import com.example.exchangeapp.io.di.itemInteractor
import com.example.exchangeapp.ui.base.BaseViewModel

class LibraryViewModel : BaseViewModel() {


    fun listenCell(): MutableLiveData<List<LibraryCell>> {
        return itemInteractor.listenList()
            .map { it.map { LibraryCell(it) } }
            .toLiveData()
    }


}