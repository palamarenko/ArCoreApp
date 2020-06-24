package com.example.exchangeapp.ui.main

import android.view.View
import ua.palamarenko.cozyandroid2.CozyCell
import com.example.exchangeapp.R
import com.example.exchangeapp.displayText
import com.example.exchangeapp.io.db.RateModel
import kotlinx.android.synthetic.main.cell_rate.view.*
import ua.palamarenko.cozyandroid2.tools.click

class RateCell(override val data: RateModel, val viewModel : MainViewModel) : CozyCell() {
    override val layout = R.layout.cell_rate

    override fun bind(view: View) {
        view.tvRate.text = data.rate.displayText()
        view.tvCurrency.text = data.currency
        view.click {
            viewModel.navigate(data.currency)
        }
    }

}