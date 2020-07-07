package com.example.exchangeapp.ui.library

import androidx.lifecycle.MutableLiveData
import com.example.exchangeapp.io.di.itemInteractor
import com.example.exchangeapp.ui.base.BaseViewModel
import com.example.exchangeapp.ui.library.add.AddFragment
import com.example.exchangeapp.ui.library.add.ID
import ua.palamarenko.cozyandroid2.base_fragment.navigation.putString
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.NAVIGATE

class LibraryViewModel : BaseViewModel() {


    fun listenCell(): MutableLiveData<List<LibraryCell>> {
        return itemInteractor.listenList()
            .map { it.map { LibraryCell(it, this) } }
            .toLiveData()
    }

    fun edit(id : String){
        task(NAVIGATE,AddFragment().putString(ID,id))
    }


}