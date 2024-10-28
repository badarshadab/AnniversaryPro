package birthdaygreetings.birthdayframe.greetingswishes.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.Window
import android.widget.TextView
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import birthdaygreetings.birthdayframe.greetingswishes.R
import java.io.File

class AddImage {
    operator fun invoke(
        context: Context,
        registry: ActivityResultRegistry,
        callback: (result: Uri?) -> Unit
    ) {
        selectImageChooseDialog(context, registry, callback)
    }

    private fun selectImageChooseDialog(
        context: Context,
        registry: ActivityResultRegistry,
        callback: (result: Uri?) -> Unit
    ) {
        val dialog = Dialog(context)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.select_image_dialog)
        val fromGallery: TextView = dialog.findViewById(R.id.fromGallery)
        val fromCamera: TextView = dialog.findViewById(R.id.fromCamera)
//        setTextGradient(fromGallery, "#D7459F", "#FF2929")
//        setTextGradient(fromCamera, "#D7459F", "#FF2929")

        fromGallery.setOnClickListener {
            val contractGallery = registry.register("", ActivityResultContracts.GetContent()) {
                callback(it)
            }
            contractGallery.launch("image/*")
            dialog.dismiss()
        }
        fromCamera.setOnClickListener {
            val cameraImgUri = createImageURI(context)
            val contractCamera = registry.register("", ActivityResultContracts.TakePicture()) {
                callback(cameraImgUri)
            }
            contractCamera.launch(cameraImgUri)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun createImageURI(context: Context): Uri {
        val imageName = "photo_" + System.currentTimeMillis() + ".jpg"
        val image = File(context.filesDir, imageName)
        return FileProvider.getUriForFile(context, context.packageName + ".provider", image)
    }

    fun setTextGradient(textView: TextView, color1: String, color2: String) {
        val paint = textView.paint
        val width = paint.measureText(textView.text.toString())
        val textShader: Shader = LinearGradient(
            0f, 0f, width, textView.textSize, intArrayOf(
                Color.parseColor(color1),
                Color.parseColor(color2),
            ), null, Shader.TileMode.REPEAT
        )

        textView.paint.shader = textShader
    }
}