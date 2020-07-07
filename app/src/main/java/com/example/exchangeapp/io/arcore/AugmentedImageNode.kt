package com.example.exchangeapp.io.arcore

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.TextureView
import android.widget.TextView
import com.example.exchangeapp.R
import com.google.ar.core.AugmentedImage
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.rendering.ViewSizer
import ua.palamarenko.cozyandroid2.tools.LOG_EVENT
import java.util.concurrent.CompletableFuture

class AugmentedImageNode(val context: Context) :
    AnchorNode() {

    var image: AugmentedImage? = null



    private val titleRendeble: CompletableFuture<ViewRenderable?> =
        ViewRenderable.builder()
            .setView(context, R.layout.custom_title)
            .setSizer(ViewSizer {
                Vector3(0.1f, 0.1f, 0.1f)

            })
            .build()


    private var maze_scale = 0.0f

    fun addImage(image: AugmentedImage) {
        this.image = image
        if (!titleRendeble.isDone) {
            CompletableFuture.allOf(titleRendeble)
                .thenAccept { aVoid: Void? ->
                    addImage(image)
                }
                .exceptionally {
                    LOG_EVENT("ERROR", "Exception loading")
                    null
                }
            return
        }
        anchor = image.createAnchor(image.centerPose)
        val maze_edge_size = 492.65f
        val max_image_edge =
            Math.max(image.extentX, image.extentZ)
        maze_scale = max_image_edge / maze_edge_size

        LOG_EVENT("HELLO", "ADD TITLE")

        val titleNode = Node()
        titleNode.setParent(this)
        titleNode.isEnabled = false
        titleRendeble.thenAccept {
            (it?.view as TextView).text = image.name

            titleNode.renderable = it
            titleNode.isEnabled = true
        }.getNow(null)

    }


}
