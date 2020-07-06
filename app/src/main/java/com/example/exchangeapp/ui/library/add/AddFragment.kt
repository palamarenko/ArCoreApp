package com.example.exchangeapp.ui.library.add

import com.example.exchangeapp.R
import com.example.exchangeapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_add.*
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.PICK_FILE
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.TOAST
import ua.palamarenko.cozyandroid2.image_picker.CROP_MODE
import ua.palamarenko.cozyandroid2.image_picker.PickSingleImageRequest
import ua.palamarenko.cozyandroid2.tools.click
import ua.palamarenko.cozyandroid2.tools.image.CornerTransform
import ua.palamarenko.cozyandroid2.tools.load
import ua.palamarenko.cozyandroid2.tools.toText
import java.io.File


class AddFragment : BaseFragment<AddViewModel>() {

    override val layout = R.layout.fragment_add

    var file : File? = null

    override fun onStartScreen() {
        super.onStartScreen()

        ivImage.click {
            task(PICK_FILE, PickSingleImageRequest(callback = {
                ivImage.load(it,CornerTransform())
                file = it
            }, cropMode = CROP_MODE.CUSTOM))

        }

        tvSave.click {
            if(file!=null  && etName.toText().isNotEmpty()){
                vm().saveItem(etName.toText(),file!!)
            }else{
                task(TOAST,"Add image or name")
            }
        }


    }


}