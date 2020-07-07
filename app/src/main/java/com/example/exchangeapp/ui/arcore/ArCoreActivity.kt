package com.example.exchangeapp.ui.arcore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exchangeapp.R
import com.example.exchangeapp.io.arcore.AugmentedImageNode
import com.google.ar.core.AugmentedImage
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.ux.ArFragment
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.CozyActivity
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.EmptyViewModel
import ua.palamarenko.cozyandroid2.tools.LOG_EVENT
import java.util.*

class ArCoreActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arcore)
    }

}