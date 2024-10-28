package birthdaygreetings.birthdayframe.greetingswishes.invitationCard

import android.app.DatePickerDialog
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.StateSet.TAG
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.databinding.FragmentBirthdayBinding
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.model.Component
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.viewModel.ComponentViewModel
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.viewModel.InvitationViewModel
import birthdaygreetings.birthdayframe.greetingswishes.model.CardShareModel
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.BusinessPref
import birthdaygreetings.birthdayframe.greetingswishes.viewmodel.IVCardShareViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.util.Calendar
import kotlin.math.pow
import kotlin.math.sqrt

class BirthdayFragment : Fragment() {
    private lateinit var binding: FragmentBirthdayBinding
    private var img_position: Int = 0
    private var bg_url: String = ""
    private var title: String = ""
    private var catPosition: Int = 0
    private lateinit var viewModel: InvitationViewModel
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month1 = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    var month3 = ""
    private var componentList1 = ArrayList<Component>()
    private var isReached = false
    lateinit var businessPref: BusinessPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bg_url = arguments?.getString("bgUrl")!!
        img_position = arguments?.getInt("imgPosition")!!
        catPosition = arguments?.getInt("catPosition")!!
        title = arguments?.getString("title").toString()
    }

//    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBirthdayBinding.inflate(layoutInflater, container, false)
//        MainFragment.pressed_btn = 10
//        AdUtils.showAdaptiveBanner(requireActivity(),binding.birthdayAd)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = title
        businessPref = BusinessPref(requireContext())

        Glide.with(requireContext()).load(bg_url).into(binding.ivPreview)
        loadContent()

//        Glide.with(requireContext())
//            .load(bg_url)
//            .placeholder(R.drawable.loading_img)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .listener(object : RequestListener<Drawable> {
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    findNavController().popBackStack()
//                    return false
//                }
//
////                @RequiresApi(Build.VERSION_CODES.O)
//                override fun onResourceReady(
//                    resource: Drawable?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    dataSource: DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    loadContent()
//                    return false
//                }
//            })
//            .into(binding.ivPreview)

        //getScreenResolution()

        binding.etAddressOfParty.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etAddressOfParty.text?.length == 30 && !isReached) {
                    binding.etAddressOfParty.append("\n")
                    isReached = true
                }
                binding.userBirthdayAddress.text = binding.etAddressOfParty.text
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etBirthdayBoyName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.userBirthdayBoyName.text = binding.etBirthdayBoyName.text
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etDateTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.userBirthdayDate.text = binding.etDateTime.text
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etAgeBoy.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.userBirthdayAge.text = binding.etAgeBoy.text
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

//        invitationPref = InvitationSharedPreference(requireContext())
//        val u_name = invitationPref.getName()
//        println("u_anme$u_name")
        buttonClick()
    }

    private fun getScreenResolution() {
        val displayMetrics = resources.displayMetrics
        val dpHeight = displayMetrics.heightPixels / displayMetrics.density
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val displayMetrics1 = DisplayMetrics()
        val windowsManager = requireContext().getSystemService(WINDOW_SERVICE) as WindowManager
        windowsManager.defaultDisplay.getMetrics(displayMetrics1)
        val deviceWidth = displayMetrics1.widthPixels
        val deviceHeight = displayMetrics1.heightPixels
        println("pixelofScreen$deviceWidth$deviceHeight")
        println("dpi${displayMetrics1.density}")
    }

//    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadContent() {
        val componentViewModel =
            ViewModelProvider(requireActivity())[ComponentViewModel::class.java]
        componentViewModel.componentList.observe(viewLifecycleOwner, Observer {
            println("compListViewModel$it")
            val displayMetrics1 = DisplayMetrics()
            val windowsManager = requireContext().getSystemService(WINDOW_SERVICE) as WindowManager
            windowsManager.defaultDisplay.getMetrics(displayMetrics1)
            var sdsd = displayMetrics1.density
            println("sdsd${displayMetrics1.density}")

            val scaleValue = requireContext().resources.displayMetrics.density
            val pixels =
                (convertPixelsToDp(it[0].com_Ypos.toFloat(), requireContext()) * scaleValue + 0.5f)

            val screenHeightInPixels = resources.displayMetrics.heightPixels
            //val screenHeightInDp = convertPixelsToDp(this, screenHeightInPixels.toFloat())

            //binding.userBirthdayBoyName.y = getActualDPsFromPixels(requireContext(),it[0].com_Ypos.substringBefore(".").toInt())
            AppUtils.setMargins(
                binding.userBirthdayBoyName,
                0,
                AppUtils.convertPixelsToDp(it[0].com_Ypos.toFloat(), requireContext()).toInt(),
                0,
                0
            )
            if (businessPref.getBirthdayName().isEmpty()) {
                binding.userBirthdayBoyName.text = resources.getText(R.string.b_name)
                //binding.etBirthdayBoyName.setText(resources.getText(R.string.b_name))
            } else {
                binding.userBirthdayBoyName.text = businessPref.getBirthdayName()
                binding.etBirthdayBoyName.setText(businessPref.getBirthdayName())
            }
            binding.userBirthdayBoyName.textSize =
                AppUtils.convertPxToSp(requireContext(), it[0].text_size.toFloat()).toFloat()
            try {
                binding.userBirthdayBoyName.setTextColor(Color.parseColor(it[0].text_color))
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
            }
            AppUtils.applyFont1(it[0].font, requireContext(), binding.userBirthdayBoyName)

            //binding.userBirthdayDate.y = getActualDPsFromPixels(requireContext(),it[1].com_Ypos.substringBefore(".").toInt())
            AppUtils.setMargins(
                binding.userBirthdayDate,
                0,
                AppUtils.convertPixelsToDp(it[1].com_Ypos.toFloat(), requireContext()).toInt(),
                0,
                0
            )
            if (businessPref.getBirthdayDate().isEmpty()) {
                binding.userBirthdayDate.text = resources.getText(R.string.party_date)
                //binding.etDateTime.setText(resources.getText(R.string.party_date))
            } else {
                binding.userBirthdayDate.text = businessPref.getBirthdayDate()
                //binding.etDateTime.text = businessPref.getBirthdayDate()
                binding.etDateTime.setText(businessPref.getBirthdayDate())
            }
            binding.userBirthdayDate.textSize =
                AppUtils.convertPxToSp(requireContext(), it[1].text_size.toFloat()).toFloat()
            try {
                binding.userBirthdayDate.setTextColor(Color.parseColor(it[1].text_color))
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
            }
            AppUtils.applyFont1(it[1].font, requireContext(), binding.userBirthdayDate)

            //binding.userBirthdayAddress.y = getActualDPsFromPixels(requireContext(),it[2].com_Ypos.substringBefore(".").toInt())
            AppUtils.setMargins(
                binding.userBirthdayAddress,
                0,
                AppUtils.convertPixelsToDp(it[2].com_Ypos.toFloat(), requireContext()).toInt(),
                0,
                0
            )
            if (businessPref.getBirthdayAddress().isEmpty()) {
                binding.userBirthdayAddress.text = resources.getText(R.string.b_party)
                //binding.etAddressOfParty.setText(resources.getText(R.string.b_party))
            } else {
                binding.userBirthdayAddress.text = businessPref.getBirthdayAddress()
                binding.etAddressOfParty.setText(businessPref.getBirthdayAddress())
            }
            binding.userBirthdayAddress.textSize =
                AppUtils.convertPxToSp(requireContext(), it[2].text_size.toFloat()).toFloat()
            try {
                binding.userBirthdayAddress.setTextColor(Color.parseColor(it[2].text_color))
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
            }
            AppUtils.applyFont1(it[2].font, requireContext(), binding.userBirthdayAddress)

            //binding.userBirthdayAge.y = getActualDPsFromPixels(requireContext(),it[3].com_Ypos.substringBefore(".").toInt())
            AppUtils.setMargins(
                binding.userBirthdayAge,
                0,
                AppUtils.convertPixelsToDp(it[3].com_Ypos.toFloat(), requireContext()).toInt(),
                0,
                0
            )
            AppUtils.setMargins(
                binding.userBirthdaySub,
                0,
                AppUtils.convertPixelsToDp(it[3].com_Ypos.toFloat(), requireContext()).toInt(),
                0,
                0
            )
            if (businessPref.getAge().isEmpty()) {
                binding.userBirthdayAge.text = resources.getText(R.string.age)
                //binding.etAgeBoy.setText(resources.getText(R.string.age))
            } else {
                binding.userBirthdayAge.text = businessPref.getAge()
                binding.etAgeBoy.setText(businessPref.getAge())
            }
            binding.userBirthdayAge.textSize =
                AppUtils.convertPxToSp(requireContext(), it[3].text_size.toFloat()).toFloat()
            try {
                binding.userBirthdayAge.setTextColor(Color.parseColor(it[3].text_color))
                binding.userBirthdaySub.setTextColor(Color.parseColor(it[3].text_color))
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
            }
            AppUtils.applyFont1(it[3].font, requireContext(), binding.userBirthdayAge)
            AppUtils.applyFont1(it[3].font, requireContext(), binding.userBirthdaySub)
        })
    }

    private fun buttonClick() {
        binding.ivCalendar.setOnClickListener {
            val dpd = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    month3 = "$dayOfMonth/${monthOfYear + 1}/$year"
                    //binding.etDateTime.text = month3
                    binding.etDateTime.setText(month3)
                },
                year,
                month1,
                day
            )
            dpd.show()
        }

        binding.btnSubmit.setOnClickListener {
//            binding.userBirthdayBoyName.text = binding.etBirthdayBoyName.text
//            binding.userBirthdayDate.text = binding.etDateTime.text
//            binding.userBirthdayAddress.text = binding.etAddressOfParty.text
//            binding.userBirthdayAge.text = binding.etAgeBoy.text

            businessPref.storeBirthdayDetails(
                binding.etBirthdayBoyName.text.toString(),
                binding.etDateTime.text.toString(),
                binding.etAddressOfParty.text.toString(),
                binding.etAgeBoy.text.toString()
            )
            //binding.scrollView2.fullScroll(ScrollView.FOCUS_UP)
            val ivCardShareViewModel: IVCardShareViewModel by lazy {
                ViewModelProvider(
                    requireActivity()
                )[IVCardShareViewModel::class.java]
            }
            AppUtils.captureScreen1(binding.crd1)
                ?.let { it1 -> ivCardShareViewModel.setImage(CardShareModel(it1)) }
            //findNavController().navigate(R.id.action_birthdayFragment_to_cardPreviewFragment)
//            AppUtils.changeFragmentNew(
//                findNavController(),
//                R.id.action_birthday_frag_to_card_prev_frag,
//                requireActivity()
//            )
            println("findNavController().currentDestination " + findNavController().currentDestination)
            if(findNavController().currentDestination?.id==R.id.birthday_invitation_frag){
                val bundle = Bundle()
                AppUtils.changeFragmentWithPosition(
                    findNavController(),
                    R.id.action_birthday_invitation_frag_to_card_prev_frag,
                    requireActivity(),
                    bundle
                )
            }

        }
    }

    fun convertPixelsToDp(px: Float, context: Context): Float {
        return px / (context.resources.displayMetrics.ydpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun pxFromDp(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }

    private fun convertPixelsToDp1(context: Context, pixels: Float): Float {
        val resource = context.resources
        val displayMetrics: DisplayMetrics = resource.displayMetrics
        val dpValue = pixels / (displayMetrics.densityDpi / 160.0F)

        val density = context.resources.displayMetrics.density
        val valueInDp = (pixels * density)
        println("dpVal$valueInDp")
        return valueInDp
    }

    private fun getActualDPsFromPixels(context: Context, pixels: Int): Float {
        val resources = context.resources
        val actualDp: Float
        val screenHeightInPixels = resources.displayMetrics.heightPixels
        val screenWithInPixels = resources.displayMetrics.heightPixels
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
        //val dpi = sqrt((screenWithInPixels * screenWithInPixels) + (screenHeightInPixels * screenHeightInPixels)) / di
//        if (resources.displayMetrics.densityDpi == DisplayMetrics.DENSITY_XHIGH){
//            actualDp = pixels / (resources.displayMetrics.densityDpi / 320f)
//        }else if (resources.displayMetrics.densityDpi == DisplayMetrics.DENSITY_XXHIGH){
//            actualDp = pixels / (resources.displayMetrics.densityDpi / 480f)
//        }else if (resources.displayMetrics.densityDpi == DisplayMetrics.DENSITY_XXXHIGH){
//            actualDp = pixels / (resources.displayMetrics.densityDpi / 640f)
//        }else if (resources.displayMetrics.densityDpi == DisplayMetrics.DENSITY_HIGH){
//            actualDp = pixels / (resources.displayMetrics.densityDpi / 240f)
//        }else{
//             actualDp = pixels / (resources.displayMetrics.densityDpi / 640f)
//        }
        val dpiLevel = resources.displayMetrics.densityDpi
        if (mScreenDPI.toDouble() == 2.0) {
            actualDp = pixels / (resources.displayMetrics.densityDpi / dpiLevel.toFloat())
        } else if (mScreenDPI < 2.0) {
            actualDp = pixels / (resources.displayMetrics.densityDpi / dpiLevel.toFloat())
        } else {
            actualDp = pixels / (resources.displayMetrics.densityDpi / 640f)
        }
        println("actu${resources.displayMetrics.densityDpi}")
        println("act${DisplayMetrics.DENSITY_XHIGH}")
        println("actualDp$actualDp")
        return actualDp
    }

    fun setToNextLine(tv: TextView, tmpString: String) {
        var index = 0
        val finalString = StringBuffer()
        while (index < tmpString.length) {
            Log.i(
                TAG,
                "test = " + tmpString.substring(index, (index + 20).coerceAtMost(tmpString.length))
            )
            finalString.append(
                tmpString.substring(
                    index,
                    (index + 20).coerceAtMost(tmpString.length)
                ) + "\n"
            )
            index += 20
        }
        tv.text = finalString
    }
}