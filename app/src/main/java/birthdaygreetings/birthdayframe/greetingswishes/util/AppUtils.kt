package birthdaygreetings.birthdayframe.greetingswishes.util

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.GravityCompat
import androidx.core.view.drawToBitmap
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.databinding.AlertDialogLayoutBinding
import birthdaygreetings.birthdayframe.greetingswishes.databinding.ShowDownloadAlertBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.card.MaterialCardView
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.sm.newadlib.listeners.FullAdListener
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.ExecutionException
import kotlin.math.pow
import kotlin.math.sqrt


object AppUtils {

    private val sharedPrefFile = "PosterMaker"

    //    var dataList = ArrayList<ViewTypeModel>()
//    lateinit var adapter: ChooseDesignAdapter
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    var swipeCount = 0

    init {

    }

//    private const val FADE_DURATION = 700
//    private val GALLERY: Int = 1
//    private val CAMERA: Int = 2
//    val RECORD_REQUEST_CODE = 101
//
//    private val PERMISSIONS_STORAGE = arrayOf(
//        Manifest.permission.READ_EXTERNAL_STORAGE,
//        Manifest.permission.WRITE_EXTERNAL_STORAGE
//    )

    fun openUrl(context: Context, url: String?) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

//    fun setImageWithRoundCorner(str: String, iv: ImageView, corner: Int, size: Int) {
//        Glide.with(iv)
//            .load(str)
//            .transform(RoundedCorners(corner))
//            .thumbnail(Glide.with(iv).load(R.drawable.loading_img))
//            .override(size)
//            .into(iv)
//    }

    fun rateUs(context: Context) {
        try {
            val i =
                Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.packageName))
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        } catch (e: java.lang.Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap {
        var width = image.width
        var height = image.height
        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    fun getBitmap(ctx: Context?, ob: Any?): Bitmap? {
        return Glide.with(ctx!!)
            .asBitmap()
            .load(ob)
            .submit()
            .get()
    }

//    fun setStatusBarBackground(activity: Activity, window: Window) {
//        activity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
//        activity.window.statusBarColor = Color.WHITE
//
//        val window: Window = window
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.statusBarColor = Color.parseColor("#DF1342")
//    }

    fun shareApp(context: Context) {
        try {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            var sAux = """
            Download
            ${context.resources.getString(R.string.app_name)}

            """.trimIndent()
            sAux = """
            ${sAux}https://play.google.com/store/apps/details?id=${context.packageName}

            """.trimIndent()
            i.putExtra(Intent.EXTRA_TEXT, sAux)
            context.startActivity(Intent.createChooser(i, "choose one"))
        } catch (e: java.lang.Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun captureScreen1(view: View): Bitmap {
        view.isDrawingCacheEnabled = true
        val bmp = Bitmap.createBitmap(view.drawingCache)
        val c = Canvas(bmp)
        c.drawColor(Color.WHITE)
        view.draw(c)
        view.isDrawingCacheEnabled = false
        return bmp
    }

    fun changeFragmentWithPosition(
        nav: NavController,
        fragmentId: Int,
        activity: Activity,
        bundle: Bundle
    ) {
        AdUtils.showFullAd(activity, object : AdUtils.AdListener {
            override fun onComplete() {
                nav.navigate(fragmentId, bundle)
            }
        })
    }

    fun changeFragmentWithPositionFromHomeFrag(
        nav: NavController,
        fragmentId: Int,
        activity: Activity,
        bundle: Bundle
    ) {
        AdUtils.showFullAdOnMainActivity1(activity, object : FullAdListener {

            override fun onComplete(isAdDisplay: Boolean, adNetwork: String) {
                nav.navigate(fragmentId, bundle)
            }
        })
    }

    fun changeTextColor(
        tvToChangeColor: TextView,
        context: Context,
        tv1: TextView,
        tv2: TextView,
        tv3: TextView,
        tv4: TextView,
        tv5: TextView,
        tv6: TextView,
        tv7: TextView,
        colorToChange: Int
    ) {
        tvToChangeColor.setTextColor(colorToChange)
        tv1.setTextColor(ContextCompat.getColor(context, R.color.unselected_text_color))
        tv1.background = null
        tv2.background = null
        tv3.background = null
        tv4.background = null
        tv5.background = null
        tv6.background = null
        tv7.background = null
        tv2.setTextColor(ContextCompat.getColor(context, R.color.unselected_text_color))
        tv3.setTextColor(ContextCompat.getColor(context, R.color.unselected_text_color))
        tv4.setTextColor(ContextCompat.getColor(context, R.color.unselected_text_color))
        tv5.setTextColor(ContextCompat.getColor(context, R.color.unselected_text_color))
        tv6.setTextColor(ContextCompat.getColor(context, R.color.unselected_text_color))
        tv7.setTextColor(ContextCompat.getColor(context, R.color.unselected_text_color))
    }

    fun changeFragmentWithPosition(nav: NavController, fragmentId: Int, activity: Activity) {
//        AdUtils.showFullAd(activity, object : AdUtils.AdListener {
//            override fun onComplete() {
        nav.navigate(fragmentId)
//            }
//        })
    }

    fun getCardCollection1(context: Context): List<String> {
        val list = ArrayList<String>()
        val file = File(context.getExternalFilesDir(null).toString() + "/Collection")
        if (file.exists()) {
            file.listFiles().forEach {
                list.add(it.path)
            }
        }
        return list.reversed()
    }

    fun shareBitmap(context: Context, bitmap: Bitmap) {
        try {
            val file = File(context.externalCacheDir, "share.png")
            val fOut = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut)
            fOut.flush()
            fOut.close()
            file.setReadable(true, false)
            shareImage(context, getProviderUri(context, file))
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            showToast(context, e.message)
        }
    }

    fun showToast(context: Context?, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun shareBitmapToWhatsapp(context: Context, bitmap: Bitmap) {
        try {
            val file = File(context.externalCacheDir, "share.png")
            val fOut = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut)
            fOut.flush()
            fOut.close()
            file.setReadable(true, false)
            shareImageToWhatsapp(context, getProviderUri(context, file))
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            showToast(context, e.message)
        }
    }

    fun shareImageToWhatsapp(ctx: Context, uri: Uri?) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setPackage("com.whatsapp")
            //intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + ctx.packageName)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            ctx.startActivity(Intent.createChooser(intent, "send..."))
        } catch (e: java.lang.Exception) {
//            showToast(ctx, "Something went wrong or WhatsApp not installed !")
            Toast.makeText(
                ctx,
                "Something went wrong or WhatsApp not installed !",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun setViewPagerAnimation(viewPager2: ViewPager2, context: Context) {
        viewPager2.apply {
            offscreenPageLimit = 1
            val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
            val currentItemHorizontalMarginPx =
                resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
            val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
            val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslationX * position
                page.scaleY = 1 - (0.05f * kotlin.math.abs(position))
            }
            setPageTransformer(pageTransformer)

            val itemDecoration = HorizontalMarginItemDecoration(
                context,
                R.dimen.viewpager_current_item_horizontal_margin
            )
            addItemDecoration(itemDecoration)
        }
    }

    fun shareGIFtoWhatsapp(ob: Any, context: Context) {
        getFile2(context, ob, object : DownloadFileListener {
            override fun onDownloadComplete(file: File?) {
                val tempFile = File(context.externalCacheDir, "share.gif")
                file?.let {
                    val f: File = file.copyTo(tempFile, true, DEFAULT_BUFFER_SIZE)
                    val uri = getProviderUri(context, f)
                    println("url$uri")
                    shareImageToWhatsapp(context, uri)
                }
            }
        })
    }

    fun getActualDPsFromPixels1(context: Context, pixels: Int): Float {
        val resources = context.resources
        val actualDp: Float
        val screenHeightInPixels = resources.displayMetrics.heightPixels
        val screenWithInPixels = resources.displayMetrics.widthPixels
        val dm = DisplayMetrics()
        val width = dm.widthPixels
        val height = dm.heightPixels
        val wi = width.toDouble() / dm.xdpi.toDouble()
        val hi = height.toDouble() / dm.ydpi.toDouble()
        val x = wi.pow(2.0)
        val y = hi.pow(2.0)
        val screenInches = sqrt(x + y)
        val mScreenDPI = resources.displayMetrics.density
        println("mScreenDPI$mScreenDPI")
        println("screenInches$screenInches")
        println("height$screenHeightInPixels")
        println("width$screenWithInPixels")

        if (mScreenDPI.toDouble() == 2.0) {
            actualDp = pixels / (resources.displayMetrics.densityDpi / 320f)
        } else if (mScreenDPI < 2.0) {
            actualDp = pixels / (resources.displayMetrics.densityDpi / 280f)
        } else if (mScreenDPI.toDouble() == 3.0) {
            actualDp = pixels / (resources.displayMetrics.densityDpi / 480f)
        } else if (mScreenDPI.toDouble() == 4.0) {
            actualDp = pixels / (resources.displayMetrics.densityDpi / 640f)
        } else {
            actualDp = pixels / (resources.displayMetrics.densityDpi / 660f)
        }
        println("actu${resources.displayMetrics.densityDpi}")
        println("act${DisplayMetrics.DENSITY_XHIGH}")
        println("actualDp$actualDp")
        println("metrices${resources.displayMetrics.ydpi}")
        val metrics: DisplayMetrics = resources.displayMetrics
        val densityDpi = (metrics.density)
        println("densityDpi$densityDpi")
        return actualDp
    }

    fun convertPixelsToDp(px: Float, context: Context): Float {
        val metrics: DisplayMetrics = context.resources.displayMetrics
        val mScreenDPI = metrics.density
        println("newmScreenDPI$mScreenDPI")
        val densityDpi = (metrics.density * 320f)
        if (mScreenDPI <= 2.0) {
            val densityDpi1 = (metrics.density * 160f)
            return px / (context.resources.displayMetrics.densityDpi.toFloat() / densityDpi1)
        } else if (mScreenDPI > 2.0 && mScreenDPI <= 3.0) {
            val densityDpi2 = (metrics.density * 240f)
            return px / (context.resources.displayMetrics.densityDpi.toFloat() / densityDpi2)
        } else {
            return px / (context.resources.displayMetrics.densityDpi.toFloat() / densityDpi)
        }
    }

    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun setMargins(v: View, l: Int, t: Int, r: Int, b: Int) {
        if (v.layoutParams is MarginLayoutParams) {
            val p = v.layoutParams as MarginLayoutParams
            //p.setMargins(l, t, r, b)
            p.topMargin = t
            v.requestLayout()
        }
    }

    fun setBusinessMargins(v: View, l: Int, t: Int, r: Int, b: Int) {
        if (v.layoutParams is MarginLayoutParams) {
            val p = v.layoutParams as MarginLayoutParams
            p.setMargins(l, t, r, b)
            v.requestLayout()
        }
    }

    fun getPixelValue(context: Context, dimenId: Int): Int {
        val resources = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dimenId.toFloat(),
            resources.displayMetrics
        ).toInt()
    }

//    fun convertPixelsToDp(px: Float, context: Context): Float {
//        val resources = context.resources
//        val metrics = resources.displayMetrics
//        return px / (metrics.densityDpi / 320f)
//    }

    fun convertPxToSp(context: Context, pxValue: Float): Int {
        val resources = context.resources
        val mScreenDPI = resources.displayMetrics.density
        println("mScreenDPIMain$mScreenDPI")
        if (mScreenDPI <= 2.0) {
            return (pxValue / context.resources.displayMetrics.scaledDensity).toInt()
        } else {
            return (pxValue / context.resources.displayMetrics.scaledDensity + 4.0f).toInt()
        }
    }

//@RequiresApi(Build.VERSION_CODES.O)
//fun applyFont(tempString: String, resources: Resources, textView: TextView) {
//    val fontFields = R.font::class.java.fields
//    for (field in fontFields) {
//        try {
//            val str2 = tempString
//            val domain: String = str2.substringBeforeLast(".")
//            if (field.name == domain) {
//                val typeface = resources.getFont(field.getInt(null))
//                textView.typeface = typeface
//                val fName = field.name
//                println("fieldName$fName")
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//}

    fun applyFont1(tempString: String, ctx: Context, textView: TextView) {
        val tf = Typeface.createFromAsset(ctx.assets, tempString)
        textView.typeface = tf
    }


    fun shareGIF(ob: Any, context: Context) {
        getFile2(context, ob, object : DownloadFileListener {
            override fun onDownloadComplete(file: File?) {
                val tempFile = File(context.externalCacheDir, "share.gif")
                file?.let {
                    val f: File = file.copyTo(tempFile, true, DEFAULT_BUFFER_SIZE)
                    val uri = getProviderUri(context, f)
                    println("url" + uri)
                    shareImage(context, uri)
                }
            }
        })
    }

    fun getFile2(ctx: Context?, ob: Any?, listener: DownloadFileListener) {
        Glide.with(ctx!!)
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

    interface DownloadFileListener {
        fun onDownloadComplete(file: File?)
    }

    fun shareImage(context: Context, uri: Uri?) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            var sAux = """
            Download ${context.getString(R.string.app_name)}

            """.trimIndent()
            sAux = """
//            ${sAux}https://play.google.com/store/apps/details?id=${context.packageName}

            """.trimIndent()
//            intent.putExtra(Intent.EXTRA_TEXT, sAux)
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.type = "image/png"
            context.startActivity(Intent.createChooser(intent, "Share image via"))
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

//    fun showDownloadAlert1(
//        nav: NavController,
//        fragmentId: Int,
//        activity: Activity,
//        bmp: Bitmap?,
//        ctx: Context
//    ) {
//        val dialog1 = Dialog(activity, R.style.Download_Theme_Dialog)
//        val backgroundDialogBinding = ShowDownloadAlertBinding.inflate(activity.layoutInflater)
//        dialog1.setContentView(backgroundDialogBinding.root)
//        dialog1.apply {
//            show()
//            backgroundDialogBinding.btnGoToCollection.setOnClickListener {
//                changeFragmentNew(nav, fragmentId, activity)
//                dismiss()
//            }
//            backgroundDialogBinding.btnOk.setOnClickListener { dismiss() }
//            Glide.with(ctx).load(bmp).override(350, 350).into(backgroundDialogBinding.savedImg)
//            backgroundDialogBinding.adDialog.let { AdUtils.showMedRect(activity, it) }
//        }
//    }

//    fun selectSavedTypeDialog(context: Activity) {
//        val builder = AlertDialog.Builder(context)
//        val customLayout: View = context.getLayoutInflater()
//            .inflate(R.layout.select_saved_dialog, null)
//        builder.setView(customLayout)
//        val dialog = builder.create()
//        val fromDaily = customLayout.findViewById<ImageView>(R.id.daily)
//        val fromHoliday = customLayout.findViewById<ImageView>(R.id.holiday)
//
//        fromDaily.setOnClickListener {
//
//            val b = Bundle()
//            b.putString("from", "daily")
//            changeFragment(context, R.id.nav_saved_main, b)
//            dialog.dismiss()
//        }
//        fromHoliday.setOnClickListener {
//
//            val b = Bundle()
//            b.putString("from", "holiday")
//            changeFragment(context, R.id.nav_saved_main, b)
//            dialog.dismiss()
//        }
//        dialog.show()
//    }

    //    fun setUpToolbar(
//        activity: AppCompatActivity,
//        toolbar: Toolbar,
//        title: String?,
//        isHomeUp: Boolean
//    ) {
//        activity.setSupportActionBar(toolbar)
////        val titleTv = toolbar.findViewById<TextView>(R.id.tooText)
////        titleTv.text = title
////        titleTv.setTextColor(activity.getColor(R.color.toolbar_text_colo))
//        activity.supportActionBar!!.setDisplayShowTitleEnabled(false)
//        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(isHomeUp)
//        activity.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_new);
//        toolbar.setNavigationOnClickListener { activity.onBackPressed() }
//    }
//
    fun setImageWithRoundCorner(str: String, iv: ImageView, corner: Int, size: Int) {
        Glide.with(iv)
            .load(str)
            .transform(RoundedCorners(corner))
            .thumbnail(Glide.with(iv).load(R.drawable.loading_img))
            .override(size)
            .into(iv)
    }

    public fun setImageWithRoundCorner1(bmp: Bitmap?, iv: ImageView) {
        Glide.with(iv)
            .load(bmp)
            .thumbnail(Glide.with(iv).load(R.drawable.loading_img))
//            .override(size)
            .into(iv)
    }

    //
    fun setImageWithRoundCorner(
        storageReference: StorageReference?,
        iv: ImageView?,
        corner: Int,
        size: Int
    ) {
        Glide.with(iv!!)
            .load(storageReference)
            .transform(RoundedCorners(corner))
            .thumbnail(Glide.with(iv).load(R.drawable.loading_img))
            .into(iv)
    }


    fun setUpViewPager2Card(
        viewPager2: ViewPager2,
        position: Int,
        ivPrev: ImageView,
        ivNext: ImageView
    ) {
        viewPager2.postDelayed(Runnable { viewPager2.currentItem = position }, 100)
        viewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    ivPrev.visibility = View.INVISIBLE
                } else {
                    ivPrev.visibility = View.VISIBLE
                }
                if (position < viewPager2.adapter?.itemCount!! - 1) {
                    ivNext.visibility = View.VISIBLE
                } else {
                    ivNext.visibility = View.INVISIBLE
                }
            }

        })
    }

    //
//    fun captureScreen(view: View): Bitmap? {
//
//        if (view == null) {
//            return null
//        }
//        view.drawToBitmap()
//        var bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888)
//        var canvas = Canvas(bitmap)
//        var drawable: Drawable = view.getBackground()
//        if (drawable != null) drawable.draw(canvas) else canvas.drawColor(android.graphics.Color.WHITE)
//        view.draw(canvas)
//
//        return bitmap
//    }
//
//    fun shareBitmap(context: Context, bitmap: Bitmap) {
//        try {
//            val file = File(context.externalCacheDir, "share.png")
//            val fOut = FileOutputStream(file)
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut)
//            fOut.flush()
//            fOut.close()
//            file.setReadable(true, false)
//            shareImage(context, getProviderUri(context, file))
//        } catch (e: java.lang.Exception) {
//            e.printStackTrace()
//            showToast(context, e.message)
//        }
//    }
//
//    fun shareImage(context: Context, uri: Uri?) {
//        try {
//            val intent = Intent(Intent.ACTION_SEND)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            var sAux = """
//            Download ${context.getString(R.string.app_name)}
//
//            """.trimIndent()
//            sAux = """
//            ${sAux}https://play.google.com/store/apps/details?id=${context.packageName}
//
//            """.trimIndent()
//            intent.putExtra(Intent.EXTRA_TEXT, sAux)
//            intent.putExtra(Intent.EXTRA_STREAM, uri)
//            intent.type = "image/png"
//            context.startActivity(Intent.createChooser(intent, "Share image via"))
//        } catch (e: java.lang.Exception) {
//            e.printStackTrace()
//        }
//    }
//
    fun getProviderUri(ctx: Context?, file: File): Uri? {
        return try {
            FileProvider.getUriForFile(
                ctx!!,
                ctx.packageName + ".provider",
                file.absoluteFile
            )
        } catch (e: java.lang.Exception) {
            null
        }
    }

    fun getProfileCollection(context: Context): List<String> {
        val list = ArrayList<String>()
        val file = File(context.getExternalFilesDir(null).toString() + "/Profile")
        if (file.exists()) {
            file.listFiles().forEach {
                list.add(it.path)
            }
        }
        return list.sorted()
    }

    //
//    fun showToast(context: Context?, msg: String?) {
//        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
//    }
//
//    fun getLocalBitmapUri(context: Context, bmp: Bitmap): Uri? {
//        var bmpUri: Uri? = null
//        try {
//            val file = File(
//                context.getExternalFilesDir(null),
//                "share_image_" + System.currentTimeMillis() + ".png"
//            )
//            val out = FileOutputStream(file)
//            bmp.compress(Bitmap.CompressFormat.PNG, 90, out)
//            out.close()
//            bmpUri = getProviderUri(context, file)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        return bmpUri
//    }
//
//    fun getFile(ctx: Context, bmp: Bitmap?): File? {
//        return try {
//            val file = File(ctx.externalCacheDir, "share.png")
//            val fOut = FileOutputStream(file)
//            bmp!!.compress(Bitmap.CompressFormat.PNG, 100, fOut)
//            fOut.flush()
//            fOut.close()
//            file.setReadable(true, false)
//            file
//        } catch (e: IOException) {
//            null
//        }
//    }
//
//    fun getBitMap(view: View?): Bitmap? {
//        if (view == null) return null
//        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(bitmap)
//        val drawable = view.background
//        if (drawable != null) drawable.draw(canvas) else canvas.drawColor(Color.WHITE)
//        view.draw(canvas)
//        return bitmap
//    }
//
//    fun shareString(context: Context, s: String?) {
//        val sendIntent = Intent()
//        sendIntent.action = Intent.ACTION_SEND
//        sendIntent.putExtra(Intent.EXTRA_TEXT, s)
//        sendIntent.type = "text/plain"
//        context.startActivity(sendIntent)
//    }
//
//    fun copyTextToClipBoard(context: Context, text: String?) {
//        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//        val clip = ClipData.newPlainText(context.getString(R.string.app_name), text)
//        clipboard.setPrimaryClip(clip)
//        Toast.makeText(context, "Text copy to clipboard", Toast.LENGTH_SHORT).show()
//    }
//
    fun setImage(ctx: Context, storageReference: StorageReference, iv: ImageView) {
        storageReference.downloadUrl.addOnSuccessListener {
            Glide.with(ctx)
                .load(it)
                .thumbnail(Glide.with(ctx).load(R.drawable.loading_img))
                .into(iv)
        }

    }

    //
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

    fun checkCameraPermission(context: Context, callback: (result: Boolean) -> Unit) {
        Dexter.withContext(context)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) { /* ... */
                    callback(true)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) { /* ... */
                    callback(false)
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) { /* ... */
                    callback(false)
                }
            }).check()
    }

    //
    fun setImage(imageView: ImageView, storageReference: StorageReference?) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.loading_img)
        requestOptions.error(R.drawable.error)
        Glide.with(imageView.context)
            .setDefaultRequestOptions(requestOptions)
            .load(storageReference).into(imageView)
    }

    //
    fun setImage(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .thumbnail(Glide.with(imageView).load(R.drawable.loading_img))
            .error(R.drawable.error)
            .into(imageView)
    }

    //
    fun changeFragmentWithPosition(
        activity: Activity,
        nav: NavController,
        fragmentId: Int,
        bundle: Bundle
    ) {
        AdUtils.showFullAd(activity, object : AdUtils.AdListener {
            override fun onComplete() {
                nav.navigate(fragmentId, bundle)
            }
        })
    }
//
//    fun changeFragment(activity: Activity, resId: Int, b: Bundle) {
////        AdUtils.showFullAd(activity, object : AdUtils.AdListener {
////            override fun onComplete() {
//        var dTime = 10L
////                if (adNetwork.equals("Mopub", ignoreCase = true)) {
////                    dTime = 500L
////                }
//        Handler(Looper.getMainLooper()).postDelayed(
//            {
//                try {
//                    Navigation.findNavController(
//                        activity,
//                        R.id.nav_host_fragment
//                    ).navigate(
//                        resId,
//                        b
//                    )
//                } catch (e: Exception) {
//                    print("catch Exception   " + e)
////                            AppUtils.getInstance().showToast(activity, "Something went wrong")
//                }
//            }, dTime
//        )
////            }
////        })
//    }

//
//    fun setScaleAnimation(view: View) {
//        val anim = ScaleAnimation(
//            0.0f,
//            1.0f,
//            0.0f,
//            1.0f,
//            Animation.RELATIVE_TO_SELF,
//            0.5f,
//            Animation.RELATIVE_TO_SELF,
//            0.5f
//        )
//        anim.duration = AppUtils.FADE_DURATION.toLong()
//        view.startAnimation(anim)
//    }
//
//
//    fun editDialog(activity: Activity) {
//        val dialog = Dialog(activity, R.style.DialogTheme)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.edit_dialog)
//
//        var name = dialog.findViewById<TextView>(R.id.name)
//        var mobile = dialog.findViewById<TextView>(R.id.mobile)
//        var email = dialog.findViewById<TextView>(R.id.email)
//        var website = dialog.findViewById<TextView>(R.id.website)
//        var adress = dialog.findViewById<TextView>(R.id.adress)
//        var adress_iv = dialog.findViewById<EditText>(R.id.adress_et)
//        var name_et = dialog.findViewById<EditText>(R.id.name_et)
//        var email_et = dialog.findViewById<EditText>(R.id.email_et)
//        var website_et = dialog.findViewById<EditText>(R.id.website_et)
//        var number_et = dialog.findViewById<EditText>(R.id.number_et)
//
//        changeText(name, name_et)
//        changeText(mobile, number_et)
//        changeText(email, email_et)
//        changeText(website, website_et)
//        changeText(adress, adress_iv)
//        dialog.show()
//    }

    fun changeText(tv: TextView, et: EditText) {
        val inputTextWatcher: TextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                tv.text = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }

        et.addTextChangedListener(inputTextWatcher)
    }

    fun workAfterClick(btn: TextView, tv: TextView, selectD: Drawable, unSelectD: Drawable) {
        if (tv.visibility == View.VISIBLE) {
            btn.background = unSelectD
            tv.visibility = View.GONE

        } else {
            btn.background = selectD
//            v!!.setTextColor(getColor(R.color.white))
            tv.visibility = View.VISIBLE
        }

    }

    fun workAfterClik(
        btn: TextView,
        tv: TextView,
        selectD: Drawable,
        unSelectD: Drawable,
        bool: Boolean
    ) {
        if (bool) {
            btn.background = unSelectD
            tv.visibility = View.GONE
        } else {
            btn.background = selectD
            tv.visibility = View.VISIBLE
        }


    }


    //
//    fun getpicGallery(activity: Activity) {
//        val intent = Intent()
//        intent.setType("image/*")
//        intent.setAction(Intent.ACTION_GET_CONTENT)
//        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY)
//    }
//
//    fun camshow(activity: Activity) {
//        try {
//
//            if (ContextCompat.checkSelfPermission(
//                    activity, Manifest.permission.ACCESS_FINE_LOCATION
//                ) !== PackageManager.PERMISSION_GRANTED
//            ) {
//                if (ActivityCompat.shouldShowRequestPermissionRationale(
//                        activity,
//                        Manifest.permission.CAMERA
//                    )
//                ) {
//                    ActivityCompat.requestPermissions(
//                        activity,
//                        arrayOf(Manifest.permission.CAMERA),
//                        1
//                    )
//                } else {
//                    ActivityCompat.requestPermissions(
//                        activity,
//                        arrayOf(Manifest.permission.CAMERA),
//                        1
//                    )
//
//                }
//            }
//
//        } catch (e: java.lang.Exception) {
//            print("errror is " + e)
//        }
//    }
//
//    fun captercamera(activity: Activity) {
//        val camera_intent =
//            Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        // Start the activity with camera_intent, and request pic id
//        activity.startActivityForResult(camera_intent, CAMERA)
//    }
//
//
    fun getBitmapFromView(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas)
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE)
        }
        view.draw(canvas)
        return returnedBitmap
    }

    interface BitmapCallback {
        fun onBitmapLoaded(bitmap: Bitmap)
        fun onFailure(e: Exception)
    }

    fun downloadAndConvertToBitmap(
        storageRef: StorageReference,
        targetWidth: Int,
        targetHeight: Int,
        callback: BitmapCallback
    ) {
        try {
            val localFile = File.createTempFile("image", "jpg")
            storageRef.getFile(localFile).addOnSuccessListener {
                try {
                    // Decode the bitmap from file
                    val options = BitmapFactory.Options()
                    options.inJustDecodeBounds = true
                    BitmapFactory.decodeFile(localFile.absolutePath, options)

                    val scaleFactor = calculateScaleFactor(
                        options.outWidth,
                        options.outHeight,
                        targetWidth,
                        targetHeight
                    )

                    options.inJustDecodeBounds = false
                    options.inSampleSize = scaleFactor

                    var bitmap = BitmapFactory.decodeFile(localFile.absolutePath, options)

                    // Make bitmap mutable
                    val mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)

                    // Resize bitmap if necessary
                    if (mutableBitmap.width != targetWidth || mutableBitmap.height != targetHeight) {
                        val matrix = Matrix()
                        matrix.postScale(
                            targetWidth.toFloat() / mutableBitmap.width,
                            targetHeight.toFloat() / mutableBitmap.height
                        )
                        bitmap = Bitmap.createBitmap(
                            mutableBitmap,
                            0,
                            0,
                            mutableBitmap.width,
                            mutableBitmap.height,
                            matrix,
                            false
                        )
                    }

                    callback.onBitmapLoaded(bitmap)
                } catch (e: Exception) {
                    callback.onFailure(e)
                }
            }.addOnFailureListener {
                callback.onFailure(it)
            }
        } catch (e: IOException) {
            callback.onFailure(e)
        }
    }

    private fun calculateScaleFactor(
        srcWidth: Int,
        srcHeight: Int,
        targetWidth: Int,
        targetHeight: Int
    ): Int {
        var inSampleSize = 1

        if (srcHeight > targetHeight || srcWidth > targetWidth) {
            val heightRatio = (srcHeight.toFloat() / targetHeight.toFloat()).toInt()
            val widthRatio = (srcWidth.toFloat() / targetWidth.toFloat()).toInt()

            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
        }

        return inSampleSize
    }

    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    fun setupToolbar(view: Toolbar, activity: Activity, drawerLayout: DrawerLayout) {
        view.setNavigationOnClickListener {
            val navController = activity.findNavController(R.id.nav_host_fragment)
            val destination = navController.currentDestination
            if (destination?.id == R.id.nav_main) {
                drawerLayout.openDrawer(GravityCompat.START)
            } else {
                navController.popBackStack()
            }
        }
    }

    fun addStringToView(
        mainView: MaterialCardView,
        context: Context,
        triple: Triple<String, Int, Typeface>
    ) {
        val string = triple.first
        val tv_sticker = BubbleTextView(
            context,
            triple.second,
            triple.third,
            0,
            string,
            mainView
        )
        removeAddedView(mainView, tv_sticker)
        tv_sticker.setOperationListener(object :
            BubbleTextView.OperationListener {
            override fun onDeleteClick() {
                removeAddedView(mainView, tv_sticker)
            }

            override fun onEdit(bubbleTextView: BubbleTextView?) {
                bubbleTextView.let {
                    val onEdit = !bubbleTextView?.isInEditMode!!
                    tv_sticker.setInEdit(onEdit)
                }
            }

            override fun onClick(bubbleTextView: BubbleTextView?) {
            }

            override fun onTop(bubbleTextView: BubbleTextView?) {
            }
        })
        if (string.length <= 50) {
            tv_sticker.setImageResource(R.mipmap.bubble_7_rb_100)
        }
        if (string.length <= 100) {
            tv_sticker.setImageResource(R.mipmap.bubble_7_rb_300_115)
        } else if (string.length > 100 && string.length < 150) {
            tv_sticker.setImageResource(R.mipmap.bubble_7_rb_500_200)
        } else if (string.length >= 150 && string.length < 400) {
            tv_sticker.setImageResource(R.mipmap.bubble_7_rb)
        } else {
            tv_sticker.setImageResource(R.mipmap.bubble_7_rb_100_100)
        }

        tv_sticker.setText(string)
        addMovableItemOnView(mainView, tv_sticker)
    }

    fun selectSavedTypeDialog(context: Activity) {
//        val sharedPreferences: SharedPreferences =
//            context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
//        val editor: SharedPreferences.Editor = sharedPreferences.edit()
//        val prevEventSize = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE).getInt("eventSize", 0)
//
//        if (prevEventSize == 0) {
//            loadDialog(0)
//            editor.putInt("eventSize", 1)
//            editor.apply()
//            editor.commit()
//        } else {
////            dataList.clear()
//            val u_name = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE).getString("U_NAME", "")
//            val u_cname =
//                context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
//                    .getString("U_CNAME", "")
//            val u_mobile =
//                context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
//                    .getString("U_MOBILE", "")
//            val u_webName =
//                context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
//                    .getString("U_WEBNAME", "")
//
////            val u_profile_pic = requireContext().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE).getString("U_PROFILE", "")
//
//            val profile : List<String> = Util.getProfileCollection(context)
//            println("profileList$profile")
//            var bmp : Bitmap? = null
//            if (profile.isNotEmpty()){
//                CoroutineScope(Dispatchers.IO).launch {
//                    bmp = AppUtils.getInstance().getBitmap(context,profile[profile.lastIndex])
//
//                    GlobalScope.launch(Dispatchers.Main) {
//
//                        backgroundList.observe(viewLifecycleOwner, Observer {
//
//                            if (dataList.isEmpty()){
//
//                                dataList.add(ViewTypeModel(ChooseDesignAdapter.VIEW_TYPE_ZERO, u_name, u_cname, u_mobile, u_webName, bmp, it[position]))
//
//                                dataList.add(ViewTypeModel(ChooseDesignAdapter.VIEW_TYPE_ONE, u_name, u_cname, u_mobile, u_webName, bmp, it[position]))
//
//                                dataList.add(ViewTypeModel(ChooseDesignAdapter.VIEW_TYPE_TWO, u_name, u_cname, u_mobile, u_webName, bmp, it[position]))
//
//                                dataList.add(ViewTypeModel(ChooseDesignAdapter.VIEW_TYPE_THREE, u_name, u_cname, u_mobile, u_webName, bmp, it[position]))
//
//                                dataList.add(ViewTypeModel(ChooseDesignAdapter.VIEW_TYPE_FOUR, u_name, u_cname, u_mobile, u_webName, bmp, it[position]))
//
//                                dataList.add(ViewTypeModel(ChooseDesignAdapter.VIEW_TYPE_FIVE, u_name, u_cname, u_mobile, u_webName, bmp, it[position]))
//                            }
//
//                            adapter = ChooseDesignAdapter(context, dataList)
//                            rvChooseDesign.adapter = adapter
//                            binding.progressBar.visibility = View.GONE
//                        })
//                    }
//                }
//            }else{
//                loadDialog(0)
//            }
//
//
////            val imgUri = u_profile_pic?.toUri()
//
//            //println("imgUri$imgUri")
//
////            Glide.with(requireContext()).load(profile[profile.lastIndex]).into(binding.ivPreview)
//        }
//        val builder = AlertDialog.Builder(context)
//        val customLayout: View = context.getLayoutInflater()
//            .inflate(R.layout.select_saved_dialog, null)
//        builder.setView(customLayout)
//        val dialog = builder.create()
//        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent);
//        dialog.show()
    }

    fun createToast(context: Context, str: String) {
//        Toast.makeText(context , str , Toast.LENGTH_SHORT).show()
    }

    fun saveProfileBitmap(ob: Bitmap, context: Context) {
        val file = getFile(context, ob)
        file?.let {
            val direct = File(
                context.getExternalFilesDir(null).toString() + "/Profile"
            )
            if (!direct.exists()) {
                direct.mkdirs()
            }
            val f = File(direct.absolutePath, "" + System.currentTimeMillis() + ".png")
            file.copyTo(f)
            createToast(context, "Saved Profile Pic")
        }
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

    fun fullExitScreen(activity: Activity) {
        var dialog = Dialog(activity)
        val appexitlayout = activity.layoutInflater.inflate(R.layout.exit_dialog, null)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(appexitlayout)
        val lout = WindowManager.LayoutParams()
        lout.copyFrom(dialog.window?.attributes)
        lout.width = WindowManager.LayoutParams.MATCH_PARENT
        lout.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog.setCancelable(true)
        dialog.window?.setAttributes(lout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        dialog.window?.statusBarColor =
            ContextCompat.getColor(activity, R.color.app_backgroudncolor)
        val title: TextView = appexitlayout.findViewById(R.id.activitytitle)
//        title.setText(R.string.exit_app)
        var backbtn: ImageView = appexitlayout.findViewById(R.id.exitbackid)
        var cancelbtn: TextView = appexitlayout.findViewById(R.id.okBtn)
        var exitbtn: TextView = appexitlayout.findViewById(R.id.canBtn)
        var exitadd: LinearLayout = appexitlayout.findViewById<LinearLayout>(R.id.exitscreenadd)
        var sharebtn: TextView = appexitlayout.findViewById(R.id.share)
        var rateusbtn: TextView = appexitlayout.findViewById(R.id.rate_us)

        backbtn.setOnClickListener {
            dialog.dismiss()
        }

        exitbtn.setOnClickListener { view: View? -> dialog.dismiss() }
        cancelbtn.setOnClickListener { view: View? -> activity.finishAffinity() }
        AdUtils.showMedRect(activity, exitadd)
//        SmAdds.showNativeAdd(activity,exitadd)
        sharebtn.setOnClickListener {
            AppUtils.shareApp(activity)
        }
        rateusbtn.setOnClickListener {
            rateUs(activity)
        }
        dialog.show()
    }

    fun changeFragment(activity: Activity, resId: Int, b: Bundle) {
//        AdUtils.showFullAd(activity, object : AdUtils.AdListener {
//            override fun onComplete() {
        var dTime = 10L
//                if (adNetwork.equals("Mopub", ignoreCase = true)) {
//                    dTime = 500L
//                }
        Handler(Looper.getMainLooper()).postDelayed(
            {
                try {
                    Navigation.findNavController(
                        activity,
                        R.id.nav_host_fragment
                    ).navigate(
                        resId,
                        b
                    )
                } catch (e: Exception) {
                    print("catch Exception   " + e)
//                            AppUtils.getInstance().showToast(activity, "Something went wrong")
                }
            }, dTime
        )
    }

    fun shareString(context: Context, s: String?) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, s)
        sendIntent.type = "text/plain"
        context.startActivity(sendIntent)
    }

    fun copyTextToClipBoard(context: Context, text: String?) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(context.getString(R.string.app_name), text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    fun captureScreen(view: View): Bitmap? {

        if (view == null) {
            return null
        }
        view.drawToBitmap()
        var bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bitmap)
        var drawable: Drawable = view.background
        if (drawable != null) drawable.draw(canvas) else canvas.drawColor(Color.WHITE)
        view.draw(canvas)

        return bitmap
    }

    fun getLocalBitmapUri(context: Context, bmp: Bitmap): Uri? {
        var bmpUri: Uri? = null
        try {
            val file = File(
                context.getExternalFilesDir(null),
                "share_image_" + System.currentTimeMillis() + ".png"
            )
            val out = FileOutputStream(file)
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.close()
            bmpUri = getProviderUri(context, file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bmpUri
    }

    fun saveBitmap(bitmap: Bitmap, activity: Activity): String? {
        var fileName: String? = "ImageName" //no .png or .jpg needed
        try {
            val bytes = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val fo: FileOutputStream =
                activity.openFileOutput(fileName, Context.MODE_PRIVATE)
            fo.write(bytes.toByteArray())
            // remember close file output
            fo.close()
        } catch (e: Exception) {
            e.printStackTrace()
            fileName = null
        }
        return fileName
    }

    private fun addMovableItemOnView(mainView: MaterialCardView, any: View) {
        mainView.addView(any)
    }

    private fun removeAddedView(mainView: MaterialCardView, view: View) {
        mainView.removeView(view)
    }

    fun saveQuotesFrames(activity: Context, view: View, extension: String, storedata: String) {
        val bm: Bitmap? = captureScreen(view)
        val file = getFile(activity, bm)
        val direct = File(
            activity.getExternalFilesDir(null).toString() + "/$storedata/" + extension
        )
        if (!direct.exists()) {
            direct.mkdirs()
        }
        val f = File(direct.absolutePath, "" + System.currentTimeMillis() + extension)
        file?.copyTo(f)

        Toast.makeText(activity, "Saved", Toast.LENGTH_SHORT).show()
    }

    fun showDownloadAlert1(
        nav: NavController,
        fragmentId: Int,
        activity: Activity,
        bmp: Bitmap?,
        ctx: Context
    ) {
        val dialog1 = Dialog(activity, R.style.Download_Theme_Dialog)
        val backgroundDialogBinding = ShowDownloadAlertBinding.inflate(activity.layoutInflater)
        dialog1.setContentView(backgroundDialogBinding.root)
        dialog1.apply {
            show()
            backgroundDialogBinding.btnGoToCollection.setOnClickListener {
                changeFragmentNew(nav, fragmentId, activity)
                dismiss()
            }
            backgroundDialogBinding.btnOk.setOnClickListener { dismiss() }
            Glide.with(ctx).load(bmp).override(350, 350).into(backgroundDialogBinding.savedImg)
            backgroundDialogBinding.adDialog.let { AdUtils.showMedRect(activity, it) }
        }
    }

    fun changeFragmentNew(nav: NavController, fragmentId: Int, activity: Activity) {
//        AdUtils.showFullAd(activity, object : AdUtils.AdListener {
//            override fun onComplete() {
        nav.navigate(fragmentId)
//            }
//        })
    }


    fun showDownloadAlert1(activity: Activity, msg1: String, msg2: String) {
        val dialog1 = Dialog(activity, R.style.Download_Theme_Dialog)
        val backgroundDialogBinding = AlertDialogLayoutBinding.inflate(activity.layoutInflater)
        AdUtils.showMedRect(activity, backgroundDialogBinding.adContainer)
        dialog1.setContentView(backgroundDialogBinding.root)
        dialog1.apply {
            show()

            Glide.with(activity).asGif().load(R.raw.celebrate).into(backgroundDialogBinding.bgId)

            backgroundDialogBinding.go.setOnClickListener {
                dialog1.dismiss()

//                SmAdds.showFullAd(activity, object : FullAdListener {
//                    override fun onComplete(isAdDisplay: Boolean, adNetwork: String) {
//                        dismiss()
//                    }
//                })
            }

            backgroundDialogBinding.ok.setOnClickListener { dismiss() }
            backgroundDialogBinding.tvMsg.text = msg1
            backgroundDialogBinding.msg.text = msg2
            backgroundDialogBinding.adContainer.let {
//                SmAdds.showNativeAdd(activity,it)
            }
        }
    }

    fun OnClickShare(view: View, context: Context) {
        val bitmap = getBitmapFromView(view)
        try {
            val file = File(context.externalCacheDir, "birthday.jpg")
            val fOut = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut)
            fOut.flush()
            fOut.close()
            file.setReadable(true, false)
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                file
            )
            val intent = Intent(Intent.ACTION_SEND)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.type = "image/gif"
            context.startActivity(intent)
//                        AdUtils.openOnAdDismiss(intent);
//            AdUtils.showFullAd(getApplicationContext(),intent , getResources().getString(R.string.fb_full_adsUnitID),getResources().getString(R.string.full_screen));
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun sendFeedBack(activity: Activity, emailId: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse(emailId) // Change to your email address
        }
        activity.startActivity(emailIntent)
    }

    fun getViewpager2SwipeCount(activity: Activity, vp: ViewPager2) {
        vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            var currentPosition = -1 // To track changes in position

            override fun onPageSelected(position: Int) {
                // Increment swipe count only if the page is changing
                if (currentPosition != -1 && currentPosition != position) {
                    swipeCount++
//                    println("swipeCount $swipeCount")
                    if (swipeCount % AdUtils.swipeCount == 0) {
                        AdUtils.showSwipeFullAd(activity, object : AdUtils.AdListener {
                            override fun onComplete() {
                            }
                        })
                    }
                }
                currentPosition = position
            }
        })
    }

}