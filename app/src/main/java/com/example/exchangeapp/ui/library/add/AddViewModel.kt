package com.example.exchangeapp.ui.library.add

import com.example.exchangeapp.io.di.itemInteractor
import com.example.exchangeapp.ui.base.BaseViewModel
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.BACK_PRESS
import java.io.File

class AddViewModel : BaseViewModel() {


    fun saveItem(name : String, file : File){
        itemInteractor.addToLibrary(name,file.absolutePath).bindSubscribe {
            task(BACK_PRESS)
        }
    }

}