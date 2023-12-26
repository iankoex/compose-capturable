// package com.example.demo.ui.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun Capturable(
    captureController: CaptureController,
    modifier: Modifier = Modifier,
    contentView: @Composable () -> Unit
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            CapturableHelperView(context, contentView = contentView).apply {
                post {
                    captureController.view = this
                }
            }
        }
    )
}

class CapturableHelperView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    val contentView: @Composable () -> Unit
) : AbstractComposeView(context, attrs, defStyleAttr) {

    @Composable
    override fun Content() {
        contentView()
    }
}

class CaptureController(
    var view: CapturableHelperView? = null
) {
    fun captureBitmap(): Bitmap? {
        view?.let { view ->
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            view.layout(view.left, view.top, view.right, view.bottom)
            view.draw(canvas)
            return bitmap
        }
        return null
    }
}
