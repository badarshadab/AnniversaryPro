//package birthdaygreetings.birthdayframe.greetingswishes.activity
//
//import android.annotation.SuppressLint
//import android.app.Dialog
//import android.content.Context
//import android.content.SharedPreferences
//import android.graphics.Bitmap
//import android.graphics.Canvas
//import android.graphics.Paint
//import android.graphics.Typeface
//import android.os.Bundle
//import android.util.DisplayMetrics
//import android.util.Log
//import android.view.View
//import android.view.ViewTreeObserver
//import android.widget.ImageView
//import android.widget.LinearLayout
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import birthdaygreetings.birthdayframe.greetingswishes.R
//import birthdaygreetings.birthdayframe.greetingswishes.database.DataBaseHelperNamePoem
//import java.io.IOException
//import java.util.Locale
//import java.util.Random
//
//class NamePoemResultactivity : AppCompatActivity() {
//    var align: String? = null
//    var b: Bundle? = null
//    var backgroundimgName = 0
//    var bgimg: ImageView? = null
//    var bitmap: Bitmap? = null
//    var bold: Typeface? = null
//    var color: String? = null
//    var dataBaseHelper: DataBaseHelperNamePoem? = null
//    var dialogR: Dialog? = null
//    var displayMetrics: DisplayMetrics? = null
//    var editor: SharedPreferences.Editor? = null
//    var f: String? = null
//    var fontarray = arrayOf(
//        "fonts/ACaslonPro-Regular.otf",
//        "fonts/HappyMonkey-Regular.ttf",
//        "fonts/Merienda-Regular.ttf",
//        "fonts/Satisfy-Regular.ttf",
//        "fonts/Gabriola.ttf",
//        "fonts/Artifika-Regular.ttf",
//        "fonts/beau___r.ttf",
//        "fonts/ChaparralPro-Regular.otf",
//        "fonts/GlassAntiqua-Regular.ttf",
//        "fonts/Sail-Regular.ttf"
//    )
//    var imgheight = 0
//    var imgname = ""
//    var imgwidth = 0
//    var line: String? = null
//    var max = 10
//    var medium: Typeface? = null
//    var message: String? = null
//    var min = 0
//    var name: String? = null
//    var number = 0
//    var r: Random? = null
//    var random = 0
//    var regular: Typeface? = null
//    lateinit var save: TextView
//    lateinit var share: TextView
//    lateinit var sharedPreferences: SharedPreferences
//    var spSize = 0
//    var tf: Typeface? = null
//    var x = 0
//    var y = 0
//    var myCanvas: MyCanvas? = null
//    var view1: View? = null
//
//    inner class MyCanvas(context: Context?) : View(context) {
//        /* access modifiers changed from: protected */
//        public override fun onDraw(canvas: Canvas) {
//            super.onDraw(canvas)
//            val paint = Paint(1)
//            if (color == "white") {
//                paint.setColor(-1)
//            } else {
//                paint.setColor(ViewCompat.MEASURED_STATE_MASK)
//            }
//            if (align == "left") {
//                x = 12
//            } else if (resources.configuration.screenLayout and 15 == 4) {
//                val namePoemResultactivity = this@NamePoemResultactivity
//                val d = Companion.height.toDouble()
//                java.lang.Double.isNaN(d)
//                namePoemResultactivity.x = (d * 0.3).toInt()
//            } else if (resources.configuration.screenLayout and 15 == 3) {
//                val namePoemResultactivity2 = this@NamePoemResultactivity
//                val d2 = Companion.height.toDouble()
//                java.lang.Double.isNaN(d2)
//                namePoemResultactivity2.x = (d2 * 0.41).toInt()
//            } else {
//                val namePoemResultactivity3 = this@NamePoemResultactivity
//                val d3 = Companion.width.toDouble()
//                java.lang.Double.isNaN(d3)
//                namePoemResultactivity3.x = (d3 * 0.39).toInt()
//            }
//            paint.textSize = spSize.toFloat() * resources.displayMetrics.scaledDensity + 0.5f
//            paint.setTypeface(tf)
//            canvas.drawText(name!!.uppercase(Locale.getDefault()), x.toFloat(), y.toFloat(), paint)
//            y += 10
//            val str = ", "
//            val str2 = ""
//            val str3 = " "
//            val str4 = "   "
//            var i = 0
//            if (name!!.length <= 4) {
//                while (i < name!!.length) {
//                    paint.setTypeface(tf)
//                    val namePoemResultactivity4 = this@NamePoemResultactivity
//                    val i2 = namePoemResultactivity4.y
//                    val d4 = imgheight.toDouble()
//                    java.lang.Double.isNaN(d4)
//                    namePoemResultactivity4.y = i2 + (d4 * 0.4).toInt() / name!!.length + 2
//                    val namePoemResultactivity5 = this@NamePoemResultactivity
//                    namePoemResultactivity5.message =
//                        namePoemResultactivity5.dataBaseHelper!!.getMessage(
//                            name!!.lowercase(Locale.getDefault())[i]
//                        )
//                    val sb = StringBuilder()
//                    sb.append(name!!.uppercase(Locale.getDefault())[i])
//                    sb.append(str4)
//                    sb.append(message)
//                    canvas.drawText(sb.toString(), x.toFloat(), y.toFloat(), paint)
//                    if (i == name!!.length - 1) {
//                        paint.setTypeface(tf)
//                        val namePoemResultactivity6 = this@NamePoemResultactivity
//                        val i3 = namePoemResultactivity6.y
//                        val d5 = imgheight.toDouble()
//                        java.lang.Double.isNaN(d5)
//                        namePoemResultactivity6.y = i3 + (d5 * 0.4).toInt() / name!!.length + 2
//                        val sb2 = StringBuilder()
//                        sb2.append(
//                            name!!.replace(str3.toRegex(), str2).uppercase(Locale.getDefault())
//                        )
//                        sb2.append(str)
//                        sb2.append(dataBaseHelper!!.getLines())
//                        canvas.drawText(sb2.toString(), x.toFloat(), y.toFloat(), paint)
//                    }
//                    i++
//                }
//            } else if (name!!.length > 7) {
//                while (i < name!!.length) {
//                    paint.setTypeface(tf)
//                    val namePoemResultactivity7 = this@NamePoemResultactivity
//                    val i4 = namePoemResultactivity7.y
//                    val d6 = imgheight.toDouble()
//                    java.lang.Double.isNaN(d6)
//                    namePoemResultactivity7.y = i4 + (d6 * 0.57).toInt() / name!!.length + 2
//                    val namePoemResultactivity8 = this@NamePoemResultactivity
//                    namePoemResultactivity8.message =
//                        namePoemResultactivity8.dataBaseHelper!!.getMessage(
//                            name!!.lowercase(Locale.getDefault())[i]
//                        )
//                    val sb3 = StringBuilder()
//                    sb3.append(name!!.uppercase(Locale.getDefault())[i])
//                    sb3.append(str4)
//                    sb3.append(message)
//                    canvas.drawText(sb3.toString(), x.toFloat(), y.toFloat(), paint)
//                    if (i == name!!.length - 1) {
//                        paint.setTypeface(tf)
//                        val namePoemResultactivity9 = this@NamePoemResultactivity
//                        val i5 = namePoemResultactivity9.y
//                        val d7 = imgheight.toDouble()
//                        java.lang.Double.isNaN(d7)
//                        namePoemResultactivity9.y = i5 + (d7 * 0.57).toInt() / name!!.length + 2
//                        val sb4 = StringBuilder()
//                        sb4.append(
//                            name!!.replace(str3.toRegex(), str2).uppercase(Locale.getDefault())
//                        )
//                        sb4.append(str)
//                        sb4.append(dataBaseHelper!!.getLines())
//                        canvas.drawText(sb4.toString(), x.toFloat(), y.toFloat(), paint)
//                    }
//                    i++
//                }
//            } else {
//                while (i < name!!.length) {
//                    paint.setTypeface(tf)
//                    val namePoemResultactivity10 = this@NamePoemResultactivity
//                    val i6 = namePoemResultactivity10.y
//                    val d8 = imgheight.toDouble()
//                    java.lang.Double.isNaN(d8)
//                    namePoemResultactivity10.y = i6 + (d8 * 0.55).toInt() / name!!.length
//                    val namePoemResultactivity11 = this@NamePoemResultactivity
//                    namePoemResultactivity11.message =
//                        namePoemResultactivity11.dataBaseHelper!!.getMessage(
//                            name!!.lowercase(Locale.getDefault())[i]
//                        )
//                    val sb5 = StringBuilder()
//                    sb5.append(name!!.uppercase(Locale.getDefault())[i])
//                    sb5.append(str4)
//                    sb5.append(message)
//                    canvas.drawText(sb5.toString(), x.toFloat(), y.toFloat(), paint)
//                    if (i == name!!.length - 1) {
//                        paint.setTypeface(tf)
//                        val namePoemResultactivity12 = this@NamePoemResultactivity
//                        val i7 = namePoemResultactivity12.y
//                        val d9 = imgheight.toDouble()
//                        java.lang.Double.isNaN(d9)
//                        namePoemResultactivity12.y = i7 + (d9 * 0.55).toInt() / name!!.length + 2
//                        val sb6 = StringBuilder()
//                        sb6.append(
//                            name!!.replace(str3.toRegex(), str2).uppercase(Locale.getDefault())
//                        )
//                        sb6.append(str)
//                        sb6.append(dataBaseHelper!!.getLines())
//                        canvas.drawText(sb6.toString(), x.toFloat(), y.toFloat(), paint)
//                    }
//                    i++
//                }
//            }
//        }
//    }
//
//    /* access modifiers changed from: protected */
//    public override fun onCreate(bundle: Bundle?) {
//        super.onCreate(bundle)
//        requestWindowFeature(1)
//        window.setFlags(1024, 1024)
//        setContentView(R.layout.activity_name_poem_resultactivity)
//        //        SMUtility.showNativeAd(findViewById(R.id.nativeAdContainer));
//        r = Random()
//        number = r!!.nextInt(max - min) + min
//        view1 = findViewById(R.id.myscreen)
//        sharedPreferences = getSharedPreferences("MYPREFERENCE", 0)
//        editor = sharedPreferences.edit()
//        random = (Math.random() * 10000.0 + 1.0).toInt()
//        if (bundle != null) {
//            random = bundle.getInt("random")
//        } else {
//            random = (Math.random() * 10000.0 + 1.0).toInt()
//        }
//        bgimg = findViewById<View>(R.id.bgimg) as ImageView
//        save = findViewById(R.id.save)
//        share = findViewById(R.id.share)
//        b = intent.extras
//        backgroundimgName = b!!.getInt("bgimg")
//        name = b!!.getString("name")
//        color = b!!.getString("color")
//        align = b!!.getString("align")
//        imgname = b!!.getString("imgname").toString()
//        bgimg!!.setImageResource(backgroundimgName)
//        val sb = StringBuilder()
//        sb.append("....")
//        sb.append(backgroundimgName)
//        Log.e("ImageName", sb.toString())
//        bgimg!!.setDrawingCacheEnabled(true)
//        bgimg!!.buildDrawingCache()
//        displayMetrics = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(displayMetrics)
//        height = displayMetrics!!.heightPixels
//        width = displayMetrics!!.widthPixels
//        dataBaseHelper = DataBaseHelperNamePoem(applicationContext)
//        bold = Typeface.createFromAsset(assets, "fonts/Neuton-Bold.ttf")
//        medium = Typeface.createFromAsset(assets, "fonts/Neuton-Light.ttf")
//        regular = Typeface.createFromAsset(assets, "fonts/Neuton-Regular.ttf")
//        tf = Typeface.createFromAsset(assets, fontarray[number])
//        val sb2 = StringBuilder()
//        sb2.append(">>>>>>")
//        sb2.append(fontarray[number])
//        sb2.append("...")
//        sb2.append(number)
//        Log.e("font", sb2.toString())
//        val i = height
//        val d = i.toDouble()
//        java.lang.Double.isNaN(d)
//        val i2 = (d * 0.09).toInt()
//        val d2 = i.toDouble()
//        java.lang.Double.isNaN(d2)
//        val layoutParams = LinearLayout.LayoutParams(i2, (d2 * 0.09).toInt())
//        val d3 = width.toDouble()
//        java.lang.Double.isNaN(d3)
//        layoutParams.setMargins((d3 * 0.02).toInt(), 0, 0, 0)
//        save.setLayoutParams(layoutParams)
//        val layoutParams2 = share.getLayoutParams()
//        val d4 = height.toDouble()
//        java.lang.Double.isNaN(d4)
//        layoutParams2.height = (d4 * 0.09).toInt()
//        val layoutParams3 = share.getLayoutParams()
//        val d5 = height.toDouble()
//        java.lang.Double.isNaN(d5)
//        layoutParams3.width = (d5 * 0.09).toInt()
//        try {
//            dataBaseHelper!!.createDatabse()
//        } catch (unused: IOException) {
//        }
//        val hashMap: HashMap<*, *> = HashMap<Any?, Any?>()
//        val str = "Birthday Name Poem Generated"
//        hashMap[str] = imgname
//        //        FlurryAgent.logEvent(str, (Map<String, String>) hashMap);
//        val str2 = imgname
//        bgimg!!.getViewTreeObserver()
//            .addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
//                override fun onPreDraw(): Boolean {
//                    bgimg!!.getViewTreeObserver().removeOnPreDrawListener(this)
//                    if (getResources().configuration.screenLayout and 15 == 4) {
//                        val layoutParams = bgimg!!.layoutParams
//                        val d = height.toDouble()
//                        java.lang.Double.isNaN(d)
//                        layoutParams.height = (d * 0.9).toInt()
//                        val layoutParams2 = bgimg!!.layoutParams
//                        val d2 = width.toDouble()
//                        java.lang.Double.isNaN(d2)
//                        layoutParams2.width = (d2 * 0.9).toInt()
//                        if (name!!.length < 7) {
//                            spSize = 24
//                        } else {
//                            spSize = 13
//                        }
//                    }
//                    if (getResources().configuration.screenLayout and 15 == 3) {
//                        val layoutParams3 = bgimg!!.layoutParams
//                        val d3 = height.toDouble()
//                        java.lang.Double.isNaN(d3)
//                        layoutParams3.height = (d3 * 0.9).toInt()
//                        val layoutParams4 = bgimg!!.layoutParams
//                        val d4 = width.toDouble()
//                        java.lang.Double.isNaN(d4)
//                        layoutParams4.width = (d4 * 0.9).toInt()
//                        if (name!!.length < 7) {
//                            spSize = 13
//                        } else {
//                            spSize = 12
//                        }
//                    } else if (name!!.length < 7) {
//                        spSize = 12
//                    } else {
//                        spSize = 11
//                    }
//                    val sb = StringBuilder()
//                    sb.append("resolution---height")
//                    val d5 = height.toDouble()
//                    java.lang.Double.isNaN(d5)
//                    sb.append((d5 * 0.9).toInt())
//                    val str = "device"
//                    Log.e(str, sb.toString())
//                    val sb2 = StringBuilder()
//                    sb2.append("resolution---width")
//                    val d6 = width.toDouble()
//                    java.lang.Double.isNaN(d6)
//                    sb2.append((d6 * 0.9).toInt())
//                    Log.e(str, sb2.toString())
//                    val namePoemResultactivity = this@NamePoemResultactivity
//                    namePoemResultactivity.imgwidth = namePoemResultactivity.bgimg!!.measuredWidth
//                    val namePoemResultactivity2 = this@NamePoemResultactivity
//                    namePoemResultactivity2.imgheight =
//                        namePoemResultactivity2.bgimg!!.measuredHeight
//                    val sb3 = StringBuilder()
//                    val str2 = ">>>>>"
//                    sb3.append(str2)
//                    sb3.append(imgheight)
//                    Log.e("imgheight", sb3.toString())
//                    val sb4 = StringBuilder()
//                    sb4.append(str2)
//                    sb4.append(imgwidth)
//                    Log.e("imgwidth", sb4.toString())
//                    val namePoemResultactivity3 = this@NamePoemResultactivity
//                    val d7 = namePoemResultactivity3.imgheight.toDouble()
//                    java.lang.Double.isNaN(d7)
//                    namePoemResultactivity3.y = (d7 * 0.25).toInt()
//                    val namePoemResultactivity4 = this@NamePoemResultactivity
//                    myCanvas = MyCanvas(namePoemResultactivity4.applicationContext)
//                    val namePoemResultactivity5 = this@NamePoemResultactivity
//                    namePoemResultactivity5.bitmap = Bitmap.createBitmap(
//                        Bitmap.createBitmap(
//                            namePoemResultactivity5.bgimg!!.drawingCache, 0, 0, imgwidth, imgheight
//                        )
//                    )
//                    val sb5 = StringBuilder()
//                    sb5.append(bitmap!!.getWidth())
//                    sb5.append(" ")
//                    sb5.append(bitmap!!.getHeight())
//                    Log.e("****Bitmap", sb5.toString())
//                    myCanvas!!.draw(Canvas(bitmap!!))
//                    bgimg!!.setImageBitmap(bitmap)
//                    val sb6 = StringBuilder()
//                    sb6.append("")
//                    sb6.append(bgimg!!.layoutParams.height)
//                    Log.e("....", sb6.toString())
//                    return true
//                }
//            })
//        save.setOnClickListener(View.OnClickListener {
//            //                View view1 = findViewById(R.id.myscreen);
////                Utils.SaveFiles(NamePoemResultactivity.this, view1, "");
//        })
//        share.setOnClickListener(View.OnClickListener {
//            //                Bitmap bitmap = Utils.getBitMap(view1);
////                SaveFiles.INSTANCE.shareBitmap(NamePoemResultactivity.this, bitmap);
//        })
//    }
//
//    override fun onRequestPermissionsResult(i: Int, strArr: Array<String>, iArr: IntArray) {
//        if (i == 111 && iArr.size > 0) {
//            val i2 = iArr[0]
//        }
//    }
//
//    fun AddRateClicks() {
//        val str = "rateagain"
//        if (sharedPreferences!!.getInt(str, 8) < 8) {
//            val i = sharedPreferences!!.getInt(str, 8) + 1
//            editor!!.putInt(str, i)
//            editor!!.commit()
//            val sb = StringBuilder()
//            sb.append("")
//            sb.append(i)
//            Log.e("Clicks: ", sb.toString())
//        }
//    }
//
//    override fun onBackPressed() {
//        val str = "rateagain"
//        if (sharedPreferences!!.getInt(str, 8) == 8) {
//            editor!!.putInt(str, 0)
//            editor!!.commit()
//            //            RATE_DIALOG();
//            return
//        }
//        super.onBackPressed()
//    }
//
//    companion object {
//        //    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 111;
//        var height = 0
//        var width = 0
//    }
//}
