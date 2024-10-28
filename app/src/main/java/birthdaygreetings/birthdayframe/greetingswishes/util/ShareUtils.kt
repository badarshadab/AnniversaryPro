package birthdaygreetings.birthdayframe.greetingswishes.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.adapter.DownloadedAdapter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object ShareUtils {

    private fun shareItem(ctx: Context, ob: Any, nameWithExtension: String, shareOnPkg: String?) {
        getFile(ctx, ob, object : DownloadFileListener {
            override fun onDownloadComplete(file: File?) {
                file?.let { _file ->
                    val tempFile = File(ctx.externalCacheDir, nameWithExtension)
                    val f: File = _file.copyTo(tempFile, true, DEFAULT_BUFFER_SIZE)
                    val uri = getProviderUri(ctx, f)
                    shareImage(ctx, uri, shareOnPkg)
                }
            }
        })
    }

    fun shareGIF(ctx: Context, ob: Any) {
        shareItem(ctx, ob, "share.gif", null)
    }

    fun shareImage(ctx: Context, ob: Any) {
        shareItem(ctx, ob, "share.png", null)
    }

    fun shareGIFOnWhatsApp(ctx: Context, ob: Any) {
        shareItem(ctx, ob, "share.gif", "com.whatsapp")
    }

    fun shareImageOnWhatsApp(ctx: Context, ob: Any) {
        shareItem(ctx, ob, "share.png", "com.whatsapp")
    }

//    fun shareQuotes(context: Context, v: View) {
//        val bm: Bitmap = AppUtils.captureScreen(v)
//        bm.let {
//            val uri: Uri = AppUtils.getLocalBitmapUri(context, bm)
//            AppUtils.shareBitmap(context, bm)
//        }
//    }

    fun shareImage(context: Context, uri: Uri, shareOnPkg: String?) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            // Compose your sharing text
            var sAux = "Download ${context.getString(R.string.app_name)}"
            sAux += " https://play.google.com/store/apps/details?id=${context.packageName}"

            // Set the package to the specific app (optional)
            intent.setPackage(shareOnPkg)

            // Put the text you want to share
            intent.putExtra(Intent.EXTRA_TEXT, sAux)

            // Put the image URI you want to share
            intent.putExtra(Intent.EXTRA_STREAM, uri)

            // Set the type to handle both text and image
            intent.type = "image/*"  // Use "*/*" to handle both text and image types

            // Start the sharing intent with a chooser
            context.startActivity(Intent.createChooser(intent, "Share image via"))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun getFile(ctx: Context, ob: Any, listener: DownloadFileListener) {
        Glide.with(ctx)
            .downloadOnly()
            .load(ob)
            .listener(object : RequestListener<File?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<File?>,
                    isFirstResource: Boolean
                ): Boolean {
                    listener.onDownloadComplete(null)
                    return false
                }

                override fun onResourceReady(
                    resource: File?,
                    model: Any,
                    target: Target<File?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    listener.onDownloadComplete(resource)
                    return false
                }
            })
            .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
    }

    fun getProviderUri(ctx: Context, file: File): Uri {
        val filePath = file.absoluteFile
        return FileProvider.getUriForFile(ctx, birthdaygreetings.birthdayframe.greetingswishes.BuildConfig.APPLICATION_ID + ".provider", filePath)
    }

    interface DownloadFileListener {
        fun onDownloadComplete(file: File?)
    }

    fun saveQuotes(activity: Activity, view: View, extension: String) {
        val bm: Bitmap = AppUtils.captureScreen(view)!!
        val file = AppUtils.getFile(activity, bm)
        val direct = File(
            activity.getExternalFilesDir(null).toString() + "/Collection/" + extension
        )
        if (!direct.exists()) {
            direct.mkdirs()
        }
        val f = File(direct.absolutePath, "" + System.currentTimeMillis() + extension)
        file?.copyTo(f)

        Toast.makeText(activity, "Saved", Toast.LENGTH_SHORT).show()
    }

    fun saveItem(activity: Activity, ob: Any, extension: String) {
        getFile(activity, ob, object : DownloadFileListener {
            override fun onDownloadComplete(file: File?) {
                val direct = File(
                    activity.getExternalFilesDir(null).toString() + "/Collection/" + extension
                )
                if (!direct.exists()) {
                    direct.mkdirs()
                }
                val f = File(direct.absolutePath, "" + System.currentTimeMillis() + extension)
                file?.copyTo(f)
            }
        })
        Toast.makeText(activity, "Saved", Toast.LENGTH_SHORT).show()
    }


    fun getCollection(activity: Activity, extension: String): ArrayList<String> {
        val list = ArrayList<String>()
        val file = File(activity.getExternalFilesDir(null).toString() + "/Collection/" + extension)
        if (file.exists()) {
            file.listFiles().forEach {
                list.add(it.path)
            }
        }
        return list
    }

    fun removeFromFavDialog(
        activity: Activity, list: ArrayList<String>,
        pos: Int,
        btnclck: String,
        adapter: DownloadedAdapter
    ) {
//        println("removeFromFavDialog is called")
        val dialog = Dialog(activity, R.style.Theme_Dialog)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.ask_remove_dialog)
        var query = dialog.findViewById<TextView>(R.id.query)
        if (btnclck.equals("Downloaded")) {
//            query.text = activity.resources.getString(R.string.down_query)
        }
//        showNativeAd(dialog.findViewById<View>(R.id.smNativeAdContainer) as ViewGroup)
        dialog.findViewById<View>(R.id.cancel)
            .setOnClickListener { view: View? -> dialog.dismiss() }
        dialog.findViewById<View>(R.id.delete).setOnClickListener { view: View? ->

            removeFavItem(
                activity,
                pos, adapter, list
            )

            dialog.dismiss()
//                adapter.notifyDataSetChanged()
//            activity.finish()
        }
        dialog.show()
    }

    //
    fun removeFavItem(
        activity: Activity?,
        pos: Int,
        adapter: DownloadedAdapter,
        list: ArrayList<String>
    ) {
        val f = File(list.get(pos))
        if (f.exists()) {
            if (f.delete()) {
                list.remove(list.get(pos))
                adapter.notifyDataSetChanged()
//                update(gifUrl,list, adapter)
                Toast.makeText(activity, "Item Deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Failed to delete", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(activity, "Item does not exist", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveBitmap(ob: Bitmap, extension: String, context: Context) {
        val file = getFile(context, ob)
        file?.let {
            val direct = File(
                context.getExternalFilesDir(null).toString() + "/Collection/" + extension
            )
            if (!direct.exists()) {
                direct.mkdirs()
            }
            val f = File(direct.absolutePath, "" + System.currentTimeMillis() + ".png")
            file.copyTo(f)
        }
        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
    }

    fun getFile(ctx: Context, bmp: Bitmap?): File? {
        return try {
            val file = File(ctx.externalCacheDir, "share.png")
            val fOut = FileOutputStream(file)
            bmp!!.compress(Bitmap.CompressFormat.PNG, 100, fOut)
            fOut.flush()
            fOut.close()
            file.setReadable(true, false)
            file
        } catch (e: IOException) {
            null
        }
    }

}