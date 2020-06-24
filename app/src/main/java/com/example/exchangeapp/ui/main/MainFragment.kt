package com.example.exchangeapp.ui.main

import com.example.exchangeapp.R
import com.example.exchangeapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*
import ua.palamarenko.cozyandroid2.tools.listen


const val BASE_CURRENCY = "USD"

class MainFragment : BaseFragment<MainViewModel>() {

    override val layout = R.layout.fragment_main

    override fun onStartScreen() {
        super.onStartScreen()
        recycler.refreshListener {
            vm().getRates(BASE_CURRENCY,true).listen(this){
                recycler.setCell(it)
            }
        }


        vm().getRates(BASE_CURRENCY).listen(this){
            recycler.setCell(it)
        }

    }


}