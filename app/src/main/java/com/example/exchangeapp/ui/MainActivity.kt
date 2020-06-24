package com.example.exchangeapp.ui

import android.os.Bundle
import com.example.exchangeapp.R
import com.example.exchangeapp.ui.main.MainFragment
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.CozyActivity
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.EmptyViewModel

class MainActivity : CozyActivity<EmptyViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        simpleInit(MainFragment())
    }
}