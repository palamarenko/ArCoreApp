package com.example.exchangeapp.ui.library.add

import androidx.lifecycle.MutableLiveData
import com.example.exchangeapp.io.db.ItemModel
import com.example.exchangeapp.io.di.itemInteractor
import com.example.exchangeapp.ui.base.BaseViewModel
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.BACK_PRESS
import ua.palamarenko.cozyandroid2.tools.LOG_EVENT
import java.io.File

class AddViewModel : BaseViewModel() {


    fun getItem(id : String) : MutableLiveData<ItemModel>{
       return itemInteractor.get(id).toLiveData()
    }

    fun saveItem(id : String, name : String, file : String){
        if(id.isNotEmpty()){
            itemInteractor.updateToLibrary(id,name,file).bindSubscribe {
                task(BACK_PRESS)
            }
        }else{
            itemInteractor.addToLibrary(name,file).bindSubscribe {
                task(BACK_PRESS)
            }
        }


    }

}