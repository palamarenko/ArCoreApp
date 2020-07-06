package com.example.exchangeapp.ui.main

import android.animation.Animator
import android.transition.TransitionManager
import android.view.ViewGroup
import com.example.exchangeapp.R
import com.example.exchangeapp.ui.arcore.ArCoreActivity
import com.example.exchangeapp.ui.base.BaseFragment
import com.example.exchangeapp.ui.library.LibraryFragment
import kotlinx.android.synthetic.main.fragment_main.*
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.NAVIGATE
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.START_ACTIVITY
import ua.palamarenko.cozyandroid2.tools.click


class MainFragment : BaseFragment<MainViewModel>() {

    override val layout = R.layout.fragment_main

    override fun onStartScreen() {
        super.onStartScreen()
        clLibrary.click {
            task(NAVIGATE,LibraryFragment())
        }

        clArcore.click {
            task(START_ACTIVITY, ArCoreActivity::class.java)

        }
    }





}