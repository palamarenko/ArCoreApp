package com.example.exchangeapp.ui.arcore

import android.app.ActivityManager
import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.example.exchangeapp.io.arcore.AugmentedImageNode
import com.google.ar.core.*
import com.google.ar.sceneform.ux.ArFragment
import ua.palamarenko.cozyandroid2.tools.LOG_EVENT
import java.io.IOException
import java.util.HashMap


class ArCoreFragment : ArFragment() {

    private val MIN_OPENGL_VERSION = 3.0
    private val DEFAULT_IMAGE_NAME = "default.jpg"
    private val DEFAULT_IMAGE_NAME2 = "default2.jpg"

    private val augmentedImageMap: MutableMap<AugmentedImage, AugmentedImageNode?> = HashMap()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val openGlVersionString =
            (context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
                .deviceConfigurationInfo
                .glEsVersion
        if (openGlVersionString.toDouble() < MIN_OPENGL_VERSION) {
            LOG_EVENT("ERROR", "Sceneform requires OpenGL ES 3.0 or later")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?
    ): View? {

        val view = super.onCreateView(inflater, container, savedInstanceState)
        planeDiscoveryController.hide()
        planeDiscoveryController.setInstructionView(null)
        arSceneView.planeRenderer.isEnabled = false
        arSceneView.scene.addOnUpdateListener { onUpdateFrame() }
        return view
    }

    override fun getSessionConfiguration(session: Session): Config {
        val config = super.getSessionConfiguration(session)
        config.focusMode = Config.FocusMode.AUTO
        if (!setupAugmentedImageDatabase(config, session)) {

            LOG_EVENT("ERROR", "Could not setup augmented image database")
        }
        return config
    }

    private fun onUpdateFrame() {
        val frame = arSceneView.arFrame
        if (frame == null || frame.camera.trackingState != TrackingState.TRACKING) {
            return
        }
        val updatedAugmentedImages =
            frame.getUpdatedTrackables(
                AugmentedImage::class.java
            )
        for (augmentedImage in updatedAugmentedImages) {
            when (augmentedImage.trackingState) {
                TrackingState.PAUSED -> {
                }
                TrackingState.TRACKING -> {
                    if (!augmentedImageMap.containsKey(augmentedImage)) {
                        val node = AugmentedImageNode(context!!)
                        node.addImage(augmentedImage)
                        augmentedImageMap[augmentedImage] = node
                        arSceneView.scene.addChild(node)
                        LOG_EVENT("HELLO","IMAGE_CREATE")
                    }
                }
                TrackingState.STOPPED -> augmentedImageMap.remove(augmentedImage)
            }
        }
    }





    private fun setupAugmentedImageDatabase(
        config: Config,
        session: Session
    ): Boolean {
        val augmentedImageDatabase: AugmentedImageDatabase
        val assetManager =
            if (context != null) context!!.assets else null
        if (assetManager == null) {
            LOG_EVENT("ERROR", "Context is null, cannot intitialize image database.")
            return false
        }

        val augmentedImageBitmap = loadAugmentedImageBitmap(
            assetManager,
            DEFAULT_IMAGE_NAME
        )
        val augmentedImageBitmap2 = loadAugmentedImageBitmap(
            assetManager,
            DEFAULT_IMAGE_NAME2
        )
        if (augmentedImageBitmap == null) {
            return false
        }
        augmentedImageDatabase = AugmentedImageDatabase(session)
        augmentedImageDatabase.addImage(
            DEFAULT_IMAGE_NAME,
            augmentedImageBitmap
        )
        augmentedImageDatabase.addImage(
            DEFAULT_IMAGE_NAME2,
            augmentedImageBitmap2
        )
        config.augmentedImageDatabase = augmentedImageDatabase
        return true
    }

    private fun loadAugmentedImageBitmap(assetManager: AssetManager, name: String): Bitmap? {
        try {
            assetManager.open(name).use { `is` -> return BitmapFactory.decodeStream(`is`) }
        } catch (e: IOException) {
            LOG_EVENT("ERROR", "IO exception loading augmented image bitmap.")
        }
        return null
    }

}