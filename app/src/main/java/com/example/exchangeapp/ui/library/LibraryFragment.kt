package com.example.exchangeapp.ui.library

import com.example.exchangeapp.R
import com.example.exchangeapp.ui.base.BaseFragment
import com.example.exchangeapp.ui.library.add.AddFragment
import kotlinx.android.synthetic.main.fragment_library.*
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.NAVIGATE
import ua.palamarenko.cozyandroid2.tools.click
import ua.palamarenko.cozyandroid2.tools.listen


class LibraryFragment : BaseFragment<LibraryViewModel>() {

    override val layout = R.layout.fragment_library

    override fun onStartScreen() {
        super.onStartScreen()
        vm().listenCell().listen(this){
            recycler.setCell(it)
        }

        tvAdd.click {
            task(NAVIGATE,AddFragment())
        }


    }


}