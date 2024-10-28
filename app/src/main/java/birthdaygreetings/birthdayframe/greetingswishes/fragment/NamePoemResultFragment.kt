package birthdaygreetings.birthdayframe.greetingswishes.fragment


import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnPreDrawListener
import android.widget.LinearLayout.LayoutParams
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import birthdaygreetings.birthdayframe.greetingswishes.databinding.ActivityNamePoemResultactivityBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.Locale
import java.util.Random


class NamePoemResultFragment : Fragment() {
    private lateinit var b: ActivityNamePoemResultactivityBinding
    var alignment = ""
    var imgColor = ""
    var userName = ""
    var imgName = ""
    var message = ""
    var x: Int = 0
    var y: Int = 0
    var number: Int = 0
    var spSize = 0
    var tf: Typeface? = null
    lateinit var bold: Typeface
    lateinit var medium: Typeface
    lateinit var regular: Typeface
    var imgheight: Int = 0
    var imgwidth: Int = 0
    var random: Int = 0
    var r: Random? = null
    lateinit var dataBaseHelper: birthdaygreetings.birthdayframe.greetingswishes.database.DataBaseHelperNamePoem
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var displayMetrics: DisplayMetrics

    lateinit var myCanvas: MyCanvas
    lateinit var bitmap: Bitmap
    var fontarray = arrayOf(
        "fonts/ACaslonPro-Regular.otf",
        "fonts/HappyMonkey-Regular.ttf",
        "fonts/Merienda-Regular.ttf",
        "fonts/Satisfy-Regular.ttf",
        "fonts/Gabriola.ttf",
        "fonts/Artifika-Regular.ttf",
        "fonts/beau___r.ttf",
        "fonts/ChaparralPro-Regular.otf",
        "fonts/GlassAntiqua-Regular.ttf",
        "fonts/Sail-Regular.ttf"
    )


    companion object {
        var height = 0
        var width = 0
        lateinit var backgroundimgName: StorageReference
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requireActivity().requestWindowFeature(1)
//        requireActivity().window.setFlags(1024, 1024)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        b = ActivityNamePoemResultactivityBinding.inflate(inflater, container, false)
        r = Random()
        number = r!!.nextInt(8) + 0
        sharedPreferences = requireContext().getSharedPreferences("MYPREFERENCE", 0)
        editor = sharedPreferences.edit()
        random = ((Math.random() * 10000.0) + 1.0).toInt()

        userName = arguments?.getString("name").toString()
        imgColor = arguments?.getString("color").toString()
        alignment = arguments?.getString("align").toString()
        imgName = arguments?.getString("imgname").toString()

        AppUtils.setImage(requireContext(), backgroundimgName, b.bgimg)
        var sb = StringBuilder()
        sb.append("....")
        sb.append(backgroundimgName)
        b.bgimg.setDrawingCacheEnabled(true)
        b.bgimg.buildDrawingCache()
        displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        height = displayMetrics.heightPixels
        width = displayMetrics.widthPixels
        dataBaseHelper =
            birthdaygreetings.birthdayframe.greetingswishes.database.DataBaseHelperNamePoem(
                requireContext()
            )
        bold = Typeface.createFromAsset(requireActivity().assets, "fonts/Neuton-Bold.ttf")
        medium = Typeface.createFromAsset(requireActivity().assets, "fonts/Neuton-Light.ttf")
        bold = Typeface.createFromAsset(requireActivity().assets, "fonts/Neuton-Regular.ttf")
        tf = Typeface.createFromAsset(requireActivity().assets, fontarray[number])
        var sb2 = StringBuilder()
        sb2.append(">>>>>>")
        sb2.append(fontarray[number])
        sb2.append("...")
        sb2.append(number)
        val i: Int = height
        val d = i.toDouble()

        java.lang.Double.isNaN(d)
        val i2 = (d * 0.09).toInt()
        val d2 = i.toDouble()
        java.lang.Double.isNaN(d2)
        var layoutParams = LayoutParams(i2, (d2 * 0.09).toInt())
        val d3: Double =
            width.toDouble()
        java.lang.Double.isNaN(d3)
        layoutParams.setMargins((d3 * 0.02).toInt(), 0, 0, 0)
        b.save.layoutParams = layoutParams
        var layoutParams2: ViewGroup.LayoutParams = b.share.layoutParams
        val d4: Double =
            height.toDouble()
        java.lang.Double.isNaN(d4)
        layoutParams2.height = (d4 * 0.09).toInt()
        val layoutParams3: ViewGroup.LayoutParams = b.share.layoutParams
        val d5: Double =
            height.toDouble()
        java.lang.Double.isNaN(d5)
        layoutParams3.width = (d5 * 0.09).toInt()
        try {
            dataBaseHelper.createDatabse()
        } catch (unused: IOException) {
        }
        val hashMap: HashMap<String, String> = HashMap<String, String>()
        val str = "Birthday Name Poem Generated"
        hashMap.put(str, imgName)
        var str2 = imgName
        b.bgimg.viewTreeObserver.addOnPreDrawListener(object : OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                b.bgimg.viewTreeObserver.removeOnPreDrawListener(this)
                if (requireActivity().resources.configuration.screenLayout and 15 == 4) {
                    var layoutParams: ViewGroup.LayoutParams = b.bgimg.layoutParams
                    var d = height.toDouble();
                    java.lang.Double.isNaN(d)
                    layoutParams.height = (d * 0.9).toInt()

                    var layoutParams2: ViewGroup.LayoutParams = b.bgimg.layoutParams
                    var d2 = height.toDouble();
                    java.lang.Double.isNaN(d2)
                    layoutParams2.width = (d2 * 0.9).toInt()
                    if (userName.length < 7) {
                        spSize = 24
                    } else {
                        spSize = 13
                    }
                }
                if (requireActivity().resources.configuration.screenLayout and 15 == 3) {
                    var layoutParams: ViewGroup.LayoutParams = b.bgimg.layoutParams
                    var d = height.toDouble();
                    java.lang.Double.isNaN(d)
                    layoutParams.height = (d * 0.9).toInt()

                    var layoutParams2: ViewGroup.LayoutParams = b.bgimg.layoutParams
                    var d2 = width.toDouble();
                    java.lang.Double.isNaN(d2)
                    layoutParams2.width = (d2 * 0.9).toInt()
                    if (userName.length < 7) {
                        spSize = 24
                    } else {
                        spSize = 13
                    }
                } else if (userName.length < 7) {
                    spSize = 12
                } else {
                    spSize = 11
                }
                var sb = StringBuilder()
                sb.append("resolution---height")
                val d5: Double = height.toDouble()
                java.lang.Double.isNaN(d5)
                sb.append((d5 * 0.9).toInt())
                val str = "device"

                val sb2 = java.lang.StringBuilder()
                sb2.append("resolution---width")
                val d6: Double = width.toDouble()
                java.lang.Double.isNaN(d6)
                sb2.append((d6 * 0.9).toInt())
                imgwidth = b.bgimg.measuredWidth
                imgheight = b.bgimg.height

                val sb3 = StringBuilder()
                val str2 = ">>>>>"
                sb3.append(str2)
                sb3.append(imgheight)

                val sb4 = StringBuilder()
                sb4.append(str2)
                sb4.append(imgwidth)

                var d7 = imgheight.toDouble()
                java.lang.Double.isNaN(d7)
                y = (d7 * 0.25).toInt()
                myCanvas = MyCanvas(requireContext())
                bitmap = Bitmap.createBitmap(
                    Bitmap.createBitmap(
                        b.bgimg.drawingCache, 0, 0,
                        imgwidth, imgheight
                    )
                )
                val sb5 = StringBuilder()
                sb5.append(bitmap.getWidth())
                sb5.append(" ")
                sb5.append(bitmap.getHeight())
                myCanvas.draw(Canvas(bitmap))
                b.bgimg.setImageBitmap(bitmap)
                val sb6 = StringBuilder()
                sb6.append("")
                sb6.append(b.bgimg.layoutParams.height)
                return true
            }

        })

        return b.root
    }


    class MyCanvas(context: Context?) : View(context) {

        var namePoemResultFragment = NamePoemResultFragment()

        /* access modifiers changed from: protected */
        public override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            val paint = Paint(1)
            if (namePoemResultFragment.imgColor == "white") {
                paint.setColor(-1)
            } else {
                paint.setColor(ViewCompat.MEASURED_STATE_MASK)
            }
            if (namePoemResultFragment.alignment == "left") {
                namePoemResultFragment.x = 12
            } else if (resources.configuration.screenLayout and 15 == 4) {

                val d: Double = NamePoemResultFragment.height.toDouble()
//                Double.NaN(d)
                namePoemResultFragment.x = (d * 0.3).toInt()
            } else if (resources.configuration.screenLayout and 15 == 3) {

                val d2: Double = NamePoemResultFragment.height.toDouble()
                java.lang.Double.isNaN(d2)
                namePoemResultFragment.x = (d2 * 0.41).toInt()
            } else {

                val d3: Double = NamePoemResultFragment.width.toDouble()
                java.lang.Double.isNaN(d3)
                namePoemResultFragment.x = (d3 * 0.39).toInt()
            }
            paint.textSize =
                namePoemResultFragment.spSize.toFloat() * resources.displayMetrics.scaledDensity + 0.5f
            paint.setTypeface(namePoemResultFragment.tf)
            canvas.drawText(
                namePoemResultFragment.userName.uppercase(Locale.getDefault()),
                namePoemResultFragment.x.toFloat(),
                namePoemResultFragment.y.toFloat(),
                paint
            )
            namePoemResultFragment.y += 10
            val str = ", "
            val str2 = ""
            val str3 = " "
            val str4 = "   "
            var i = 0
            if (namePoemResultFragment.userName.length <= 4) {
                while (i < namePoemResultFragment.userName.length) {
                    paint.setTypeface(namePoemResultFragment.tf)

                    val i2: Int = namePoemResultFragment.y
                    val d4: Double = namePoemResultFragment.imgheight.toDouble()
                    java.lang.Double.isNaN(d4)
                    namePoemResultFragment.y =
                        i2 + (d4 * 0.4).toInt() / namePoemResultFragment.userName.length + 2

                    namePoemResultFragment.message =
                        namePoemResultFragment.dataBaseHelper.getMessage(
                            namePoemResultFragment.userName.lowercase(
                                Locale.getDefault()
                            ).get(i)
                        )
                    val sb = StringBuilder()
                    sb.append(
                        namePoemResultFragment.userName.uppercase(Locale.getDefault()).get(i)
                    )
                    sb.append(str4)
                    sb.append(namePoemResultFragment.message)
                    canvas.drawText(
                        sb.toString(),
                        namePoemResultFragment.x.toFloat(),
                        namePoemResultFragment.y.toFloat(),
                        paint
                    )
                    if (i == namePoemResultFragment.userName.length - 1) {
                        paint.setTypeface(namePoemResultFragment.tf)

                        val i3: Int = namePoemResultFragment.y
                        val d5: Double = namePoemResultFragment.imgheight.toDouble()
                        java.lang.Double.isNaN(d5)
                        namePoemResultFragment.y =
                            i3 + (d5 * 0.4).toInt() / namePoemResultFragment.userName.length + 2
                        val sb2 = StringBuilder()
                        sb2.append(
                            namePoemResultFragment.userName.replace(str3.toRegex(), str2).uppercase(
                                Locale.getDefault()
                            )
                        )
                        sb2.append(str)
                        sb2.append(namePoemResultFragment.dataBaseHelper.getLines())
                        canvas.drawText(
                            sb2.toString(),
                            namePoemResultFragment.x.toFloat(),
                            namePoemResultFragment.y.toFloat(),
                            paint
                        )
                    }
                    i++
                }
            } else if (namePoemResultFragment.userName.length > 7) {
                while (i < namePoemResultFragment.userName.length) {
                    paint.setTypeface(namePoemResultFragment.tf)

                    val i4: Int = namePoemResultFragment.y
                    val d6: Double = namePoemResultFragment.imgheight.toDouble()
                    java.lang.Double.isNaN(d6)
                    namePoemResultFragment.y =
                        i4 + (d6 * 0.57).toInt() / namePoemResultFragment.userName.length + 2

                    namePoemResultFragment.message =
                        namePoemResultFragment.dataBaseHelper.getMessage(
                            namePoemResultFragment.userName.lowercase(
                                Locale.getDefault()
                            ).get(i)
                        )
                    val sb3 = StringBuilder()
                    sb3.append(
                        namePoemResultFragment.userName.uppercase(Locale.getDefault()).get(i)
                    )
                    sb3.append(str4)
                    sb3.append(namePoemResultFragment.message)
                    canvas.drawText(
                        sb3.toString(),
                        namePoemResultFragment.x.toFloat(),
                        namePoemResultFragment.y.toFloat(),
                        paint
                    )
                    if (i == namePoemResultFragment.userName.length - 1) {
                        paint.setTypeface(namePoemResultFragment.tf)

                        val i5: Int = namePoemResultFragment.y
                        val d7: Double = namePoemResultFragment.imgheight.toDouble()
                        java.lang.Double.isNaN(d7)
                        namePoemResultFragment.y =
                            i5 + (d7 * 0.57).toInt() / namePoemResultFragment.userName.length + 2
                        val sb4 = StringBuilder()
                        sb4.append(
                            namePoemResultFragment.userName.replace(str3.toRegex(), str2).uppercase(
                                Locale.getDefault()
                            )
                        )
                        sb4.append(str)
                        sb4.append(namePoemResultFragment.dataBaseHelper.getLines())
                        canvas.drawText(
                            sb4.toString(),
                            namePoemResultFragment.x.toFloat(),
                            namePoemResultFragment.y.toFloat(),
                            paint
                        )
                    }
                    i++
                }
            } else {
                while (i < namePoemResultFragment.userName.length) {
                    paint.setTypeface(namePoemResultFragment.tf)

                    val i6: Int = namePoemResultFragment.y
                    val d8: Double = namePoemResultFragment.imgheight.toDouble()
                    java.lang.Double.isNaN(d8)
                    namePoemResultFragment.y =
                        i6 + (d8 * 0.55).toInt() / namePoemResultFragment.userName.length

                    namePoemResultFragment.message =
                        namePoemResultFragment.dataBaseHelper.getMessage(
                            namePoemResultFragment.userName.lowercase(
                                Locale.getDefault()
                            ).get(i)
                        )
                    val sb5 = StringBuilder()
                    sb5.append(
                        namePoemResultFragment.userName.uppercase(Locale.getDefault()).get(i)
                    )
                    sb5.append(str4)
                    sb5.append(namePoemResultFragment.message)
                    canvas.drawText(
                        sb5.toString(),
                        namePoemResultFragment.x.toFloat(),
                        namePoemResultFragment.y.toFloat(),
                        paint
                    )
                    if (i == namePoemResultFragment.userName.length - 1) {
                        paint.setTypeface(namePoemResultFragment.tf)

                        val i7: Int = namePoemResultFragment.y
                        val d9: Double = namePoemResultFragment.imgheight.toDouble()
                        java.lang.Double.isNaN(d9)
                        namePoemResultFragment.y =
                            i7 + (d9 * 0.55).toInt() / namePoemResultFragment.userName.length + 2
                        val sb6 = StringBuilder()
                        sb6.append(
                            namePoemResultFragment.userName.replace(str3.toRegex(), str2).uppercase(
                                Locale.getDefault()
                            )
                        )
                        sb6.append(str)
                        sb6.append(namePoemResultFragment.dataBaseHelper.getLines())
                        canvas.drawText(
                            sb6.toString(),
                            namePoemResultFragment.x.toFloat(),
                            namePoemResultFragment.y.toFloat(),
                            paint
                        )
                    }
                    i++
                }
            }
        }
    }


}