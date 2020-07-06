package com.example.exchangeapp.ui.library

import android.view.View
import ua.palamarenko.cozyandroid2.CozyCell
import com.example.exchangeapp.R
import com.example.exchangeapp.io.db.ItemModel
import kotlinx.android.synthetic.main.cell_library.view.*
import ua.palamarenko.cozyandroid2.tools.image.CircleTransform
import ua.palamarenko.cozyandroid2.tools.load
import java.io.File

class LibraryCell(override val data: ItemModel) : CozyCell() {

    override val layout = R.layout.cell_library

    override fun bind(view: View) {
        view.ivIcon.load(File(data.imagePlace),CircleTransform())
        view.tvName.text = data.name
    }

}