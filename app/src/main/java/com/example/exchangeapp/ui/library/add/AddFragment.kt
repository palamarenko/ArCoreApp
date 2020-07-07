package com.example.exchangeapp.ui.library.add

import com.example.exchangeapp.R
import com.example.exchangeapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_add.*
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.PICK_FILE
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.TOAST
import ua.palamarenko.cozyandroid2.image_picker.CROP_MODE
import ua.palamarenko.cozyandroid2.image_picker.PickFileRequest
import ua.palamarenko.cozyandroid2.image_picker.PickSingleImageRequest
import ua.palamarenko.cozyandroid2.tools.click
import ua.palamarenko.cozyandroid2.tools.image.CornerTransform
import ua.palamarenko.cozyandroid2.tools.listen
import ua.palamarenko.cozyandroid2.tools.load
import ua.palamarenko.cozyandroid2.tools.toText
import java.io.File


const val ID = "ID"

class AddFragment : BaseFragment<AddViewModel>() {

    override val layout = R.layout.fragment_add

    var file: String? = null

    override fun onStartScreen() {
        super.onStartScreen()

        if (getArgumentString(ID).isNotEmpty())
            vm().getItem(getArgumentString(ID)).listen(this) {
                etName.setText(it.name)
                file = it.imagePlace
                ivImage.load(File(it.imagePlace), CornerTransform())
            }

        ivImage.click {
            task(PICK_FILE, PickSingleImageRequest(callback = {
                ivImage.load(it, CornerTransform())
                file = it.absolutePath
            }, cropMode = CROP_MODE.CUSTOM))


        }

        tvSave.click {
            if (file != null && etName.toText().isNotEmpty()) {
                vm().saveItem(getArgumentString(ID), etName.toText(), file?:"")
            } else {
                task(TOAST, "Add image or name")
            }
        }


    }


}