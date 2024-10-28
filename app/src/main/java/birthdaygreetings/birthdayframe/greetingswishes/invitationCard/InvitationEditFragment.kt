package birthdaygreetings.birthdayframe.greetingswishes.invitationCard

import android.app.DatePickerDialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.databinding.FragmentInvitationPreviewBinding
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.viewModel.ComponentViewModel
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.viewModel.InvitationViewModel
import birthdaygreetings.birthdayframe.greetingswishes.model.CardShareModel
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.BusinessPref
import birthdaygreetings.birthdayframe.greetingswishes.viewmodel.IVCardShareViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.util.Calendar


class InvitationEditFragment : Fragment() {
    private lateinit var binding: FragmentInvitationPreviewBinding
    private var invList = ArrayList<String>()
    private var img_position: Int = 0
    private var bg_url: String = ""
    private var catPosition: Int = 0
    var inPos = "15"
    var title = ""
    private lateinit var viewModel: InvitationViewModel
    val c = Calendar.getInstance()
    private val year = c.get(Calendar.YEAR)
    private val month1 = c.get(Calendar.MONTH)
    private val day = c.get(Calendar.DAY_OF_MONTH)
    private var month3 = ""
    private var isReached = false
    private lateinit var businessPref: BusinessPref




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
        binding = FragmentInvitationPreviewBinding.inflate(layoutInflater, container, false)
//        MainFragment.pressed_btn = 10
        businessPref = BusinessPref(requireContext())
        AdUtils.showAdaptiveBanner(requireActivity(), binding.invEditAd)

        Glide.with(requireContext())
            .load(bg_url)
            .placeholder(R.drawable.loading_img)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.ivPreview)
        setbuttonVisibility()
        setData()
//        try {
//            Glide.with(requireContext())
//                .load(bg_url)
//                .placeholder(R.drawable.loading_img)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .listener(object : RequestListener<Drawable> {
//                    override fun onLoadFailed(
//                        e: GlideException?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        return false
//                    }
//
//                    override fun onResourceReady(
//                        resource: Drawable?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        dataSource: DataSource?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        isLoaded = true
//                        setbuttonVisibility()
//                        setData()
//                        //invitationCardManager = InvitationManager(requireContext())
//                        //observeData()
//                        return false
//                    }
//                })
//                .into(binding.ivPreview)
//        } catch (e: Exception) {
//            Toast.makeText(requireContext(), "error... ${e.message}", Toast.LENGTH_SHORT).show()
//            findNavController().popBackStack()
//        }

        //setbuttonVisibility()

        binding.etVenue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etVenue.text?.length == 30 && !isReached) {
                    binding.etVenue.append("\n")
                    isReached = true
                }
                binding.userAddress.text = binding.etVenue.text
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.userDate.text = binding.etDate.text
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etBrideName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.userBrideName.text = binding.etBrideName.text
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etBrideFatherName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.userBrideFatherName.text = binding.etBrideFatherName.text
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etBrideMotherName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.userBrideMotherName.text = binding.etBrideMotherName.text
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etGroomName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.userGroomName.text = binding.etGroomName.text
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etGroomFatherName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.userGroomFatherName.text = binding.etGroomFatherName.text
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etGroomMotherName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.userGroomMotherName.text = binding.etGroomMotherName.text
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etContactNo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.userContact.text = binding.etContactNo.text
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        buttonClick()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = title
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setbuttonVisibility() {
        if (title == "Anniversary Invitation") {
            binding.etBrideFatherName.visibility = View.GONE
            binding.etBrideMotherName.visibility = View.GONE
            binding.etGroomMotherName.visibility = View.GONE
            binding.etGroomFatherName.visibility = View.GONE
//            binding.tvBrideFatherName.visibility = View.GONE
//            binding.tvBrideMotherName.visibility = View.GONE
//            binding.tvGroomFatherName.visibility = View.GONE
//            binding.tvGroomMotherName.visibility = View.GONE
        } else {
            binding.etBrideFatherName.visibility = View.VISIBLE
            binding.etBrideMotherName.visibility = View.VISIBLE
            binding.etGroomMotherName.visibility = View.VISIBLE
            binding.etGroomFatherName.visibility = View.VISIBLE
//            binding.tvBrideFatherName.visibility = View.VISIBLE
//            binding.tvBrideMotherName.visibility = View.VISIBLE
//            binding.tvGroomFatherName.visibility = View.VISIBLE
//            binding.tvGroomMotherName.visibility = View.VISIBLE
        }
    }

    //    @RequiresApi(Build.VERSION_CODES.O)
    private fun setData() {
        val componentViewModel: ComponentViewModel by lazy { ViewModelProvider(requireActivity())[ComponentViewModel::class.java] }
        componentViewModel.componentList.observe(viewLifecycleOwner, Observer {
            if (title == "Wedding Invitation") {
                AppUtils.setMargins(
                    binding.userAddress,
                    0,
                    AppUtils.convertPixelsToDp(it[8].com_Ypos.toFloat(), requireContext()).toInt(),
                    0,
                    0
                )
                //binding.userAddress.y = AppUtils.getActualDPsFromPixels1(requireContext(),it[8].com_Ypos.toInt())
                //binding.userAddress.y = AppUtils.convertPixelsToDp(it[8].com_Ypos.toFloat(),requireContext())
                println(
                    "newdp${
                        AppUtils.convertPixelsToDp(
                            it[8].com_Ypos.toFloat(),
                            requireContext()
                        )
                    }"
                )

                val lp = binding.userAddress.layoutParams as ConstraintLayout.LayoutParams
                val px = AppUtils.convertDpToPixel(
                    AppUtils.convertPixelsToDp(
                        it[8].com_Ypos.toFloat(),
                        requireContext()
                    ), requireContext()
                )
                val pixels: Float = AppUtils.convertPixelsToDp(
                    it[8].com_Ypos.toFloat(),
                    requireContext()
                ) * requireContext()!!.resources.displayMetrics.density
                //lp.topMargin = AppUtils.getPixelValue(requireContext(),it[8].com_Ypos.toInt())

                if (businessPref.getANVenue().isEmpty()) {
                    binding.userAddress.text = resources.getText(R.string.b_party)
                    //binding.etVenue.setText(resources.getText(R.string.b_party))
                } else {
                    binding.userAddress.text = businessPref.getANVenue()
                    binding.etVenue.setText(businessPref.getANVenue())
                }
                binding.userAddress.textSize =
                    convertStringToFloat(it[8].text_size.toFloat(), requireContext().resources)
                try {
                    binding.userAddress.setTextColor(Color.parseColor(it[8].text_color))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
                applyFont(it[8].font, requireContext().resources, binding.userAddress)

                //binding.userDate.y = AppUtils.getActualDPsFromPixels1(requireContext(),it[6].com_Ypos.toInt())
                AppUtils.setMargins(
                    binding.userDate,
                    0,
                    AppUtils.convertPixelsToDp(it[6].com_Ypos.toFloat(), requireContext()).toInt(),
                    0,
                    0
                )
                if (businessPref.getANDate().isEmpty()) {
                    binding.userDate.text = resources.getText(R.string.party_date)
                    //binding.etDate.text = "12/08/2023"
                    //binding.etDate.setText(resources.getText(R.string.party_date))
                } else {
                    binding.userDate.text = businessPref.getANDate()
                    //binding.etDate.text = businessPref.getANDate()
                    binding.etDate.setText(businessPref.getANDate())
                }
                binding.userDate.textSize =
                    convertStringToFloat(it[6].text_size.toFloat(), requireContext().resources)
                try {
                    binding.userDate.setTextColor(Color.parseColor(it[6].text_color))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
                applyFont(it[6].font, requireContext().resources, binding.userDate)

                // binding.userContact.y =AppUtils.getActualDPsFromPixels1(requireContext(),it[7].com_Ypos.toInt())
                AppUtils.setMargins(
                    binding.userContact,
                    0,
                    AppUtils.convertPixelsToDp(it[7].com_Ypos.toFloat(), requireContext()).toInt(),
                    0,
                    0
                )
                if (businessPref.getANContact().isEmpty()) {
                    binding.userContact.text = resources.getText(R.string.mobile)
                    //binding.etContactNo.setText(resources.getText(R.string.mobile))
                } else {
                    binding.userContact.text = businessPref.getANContact()
                    binding.etContactNo.setText(businessPref.getANContact())
                }
                binding.userContact.textSize =
                    convertStringToFloat(it[7].text_size.toFloat(), requireContext().resources)
                try {
                    binding.userContact.setTextColor(Color.parseColor(it[7].text_color))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
                applyFont(it[7].font, requireContext().resources, binding.userContact)

                //binding.userGroomName.y = AppUtils.getActualDPsFromPixels1(requireContext(),it[3].com_Ypos.toInt())
                AppUtils.setMargins(
                    binding.userGroomName,
                    0,
                    AppUtils.convertPixelsToDp(it[3].com_Ypos.toFloat(), requireContext()).toInt(),
                    0,
                    0
                )
                if (businessPref.getANgName().isEmpty()) {
                    binding.userGroomName.text = resources.getText(R.string.groom_name)
                    //binding.etGroomName.setText(resources.getText(R.string.groom_name))
                } else {
                    binding.userGroomName.text = businessPref.getANgName()
                    binding.etGroomName.setText(businessPref.getANgName())
                }
                binding.userGroomName.textSize =
                    convertStringToFloat(it[3].text_size.toFloat(), requireContext().resources)
                try {
                    binding.userGroomName.setTextColor(Color.parseColor(it[3].text_color))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
                applyFont(it[3].font, requireContext().resources, binding.userGroomName)

                //binding.userGroomFatherName.y = AppUtils.getActualDPsFromPixels1(requireContext(),it[4].com_Ypos.toInt())
                AppUtils.setMargins(
                    binding.userGroomFatherName,
                    0,
                    AppUtils.convertPixelsToDp(it[4].com_Ypos.toFloat(), requireContext()).toInt(),
                    0,
                    0
                )
                if (businessPref.getGroomFName().isEmpty()) {
                    binding.userGroomFatherName.text = resources.getText(R.string.groom_father_name)
                    //binding.etGroomFatherName.setText(resources.getText(R.string.groom_father_name))
                } else {
                    binding.userGroomFatherName.text = businessPref.getGroomFName()
                    binding.etGroomFatherName.setText(businessPref.getGroomFName())
                }
                binding.userGroomFatherName.textSize =
                    convertStringToFloat(it[4].text_size.toFloat(), requireContext().resources)
                try {
                    binding.userGroomFatherName.setTextColor(Color.parseColor(it[4].text_color))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
                applyFont(it[4].font, requireContext().resources, binding.userGroomFatherName)

                //binding.userGroomMotherName.y = AppUtils.getActualDPsFromPixels1(requireContext(),it[5].com_Ypos.toInt())
                AppUtils.setMargins(
                    binding.userGroomMotherName,
                    0,
                    AppUtils.convertPixelsToDp(it[5].com_Ypos.toFloat(), requireContext()).toInt(),
                    0,
                    0
                )
                if (businessPref.getGroomMName().isEmpty()) {
                    binding.userGroomMotherName.text = resources.getText(R.string.groom_mother_name)
                    //binding.etGroomMotherName.setText(resources.getText(R.string.groom_mother_name))
                } else {
                    binding.userGroomMotherName.text = businessPref.getGroomMName()
                    binding.etGroomMotherName.setText(businessPref.getGroomMName())
                }
                binding.userGroomMotherName.textSize =
                    convertStringToFloat(it[5].text_size.toFloat(), requireContext().resources)
                try {
                    binding.userGroomMotherName.setTextColor(Color.parseColor(it[5].text_color))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
                applyFont(it[5].font, requireContext().resources, binding.userGroomMotherName)

                //binding.userBrideName.y = AppUtils.getActualDPsFromPixels1(requireContext(),it[0].com_Ypos.toInt())
                AppUtils.setMargins(
                    binding.userBrideName,
                    0,
                    AppUtils.convertPixelsToDp(it[0].com_Ypos.toFloat(), requireContext()).toInt(),
                    0,
                    0
                )
                if (businessPref.getANbName().isEmpty()) {
                    binding.userBrideName.text = resources.getText(R.string.bride_name)
                    //binding.etBrideName.setText(resources.getText(R.string.bride_name))
                } else {
                    binding.userBrideName.text = businessPref.getANbName()
                    binding.etBrideName.setText(businessPref.getANbName())
                }
                binding.userBrideName.textSize =
                    convertStringToFloat(it[0].text_size.toFloat(), requireContext().resources)
                try {
                    binding.userBrideName.setTextColor(Color.parseColor(it[0].text_color))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
                applyFont(it[0].font, requireContext().resources, binding.userBrideName)

                //binding.userBrideFatherName.y = AppUtils.getActualDPsFromPixels1(requireContext(),it[1].com_Ypos.toInt())
                AppUtils.setMargins(
                    binding.userBrideFatherName,
                    0,
                    AppUtils.convertPixelsToDp(it[1].com_Ypos.toFloat(), requireContext()).toInt(),
                    0,
                    0
                )
                if (businessPref.getBrideFName().isEmpty()) {
                    binding.userBrideFatherName.text = resources.getText(R.string.bride_father_name)
                    //binding.etBrideFatherName.setText(resources.getText(R.string.bride_father_name))
                } else {
                    binding.userBrideFatherName.text = businessPref.getBrideFName()
                    binding.etBrideFatherName.setText(businessPref.getBrideFName())
                }
                binding.userBrideFatherName.textSize =
                    convertStringToFloat(it[1].text_size.toFloat(), requireContext().resources)
                try {
                    binding.userBrideFatherName.setTextColor(Color.parseColor(it[1].text_color))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
                applyFont(it[1].font, requireContext().resources, binding.userBrideFatherName)

                //binding.userBrideMotherName.y = AppUtils.getActualDPsFromPixels1(requireContext(),it[2].com_Ypos.toInt())
                AppUtils.setMargins(
                    binding.userBrideMotherName,
                    0,
                    AppUtils.convertPixelsToDp(it[2].com_Ypos.toFloat(), requireContext()).toInt(),
                    0,
                    0
                )
                if (businessPref.getBrideMName().isEmpty()) {
                    binding.userBrideMotherName.text = resources.getText(R.string.bride_mother_name)
                    //binding.etBrideMotherName.setText(resources.getText(R.string.bride_mother_name))
                } else {
                    binding.userBrideMotherName.text = businessPref.getBrideMName()
                    binding.etBrideMotherName.setText(businessPref.getBrideMName())
                }
                binding.userBrideMotherName.textSize =
                    convertStringToFloat(it[2].text_size.toFloat(), requireContext().resources)
                try {
                    binding.userBrideMotherName.setTextColor(Color.parseColor(it[2].text_color))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
                applyFont(it[2].font, requireContext().resources, binding.userBrideMotherName)
            } else if (title == "Anniversary Invitation") {
                //binding.userBrideName.y = AppUtils.getActualDPsFromPixels1(requireContext(),it[0].com_Ypos.substringBefore(".").toInt())
                AppUtils.setMargins(
                    binding.userBrideName,
                    0,
                    AppUtils.convertPixelsToDp(it[0].com_Ypos.toFloat(), requireContext()).toInt(),
                    0,
                    0
                )
                if (businessPref.getANbName().isEmpty()) {
                    binding.userBrideName.text = resources.getText(R.string.bride_name)
                    //binding.etBrideName.setText(resources.getText(R.string.bride_name))
                } else {
                    binding.userBrideName.text = businessPref.getANbName()
                    binding.etBrideName.setText(businessPref.getANbName())
                }
                binding.userBrideName.textSize =
                    convertStringToFloat(it[0].text_size.toFloat(), requireContext().resources)
                try {
                    binding.userBrideName.setTextColor(Color.parseColor(it[0].text_color))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
                applyFont(it[0].font, requireContext().resources, binding.userBrideName)

                //binding.userGroomName.y = AppUtils.getActualDPsFromPixels1(requireContext(),it[1].com_Ypos.substringBefore(".").toInt())
                AppUtils.setMargins(
                    binding.userGroomName,
                    0,
                    AppUtils.convertPixelsToDp(it[1].com_Ypos.toFloat(), requireContext()).toInt(),
                    0,
                    0
                )
                if (businessPref.getANgName().isEmpty()) {
                    binding.userGroomName.text = resources.getText(R.string.groom_name)
                    //binding.etGroomName.setText(resources.getText(R.string.groom_name))
                } else {
                    binding.userGroomName.text = businessPref.getANgName()
                    binding.etGroomName.setText(businessPref.getANgName())
                }
                binding.userGroomName.textSize =
                    convertStringToFloat(it[1].text_size.toFloat(), requireContext().resources)
                try {
                    binding.userGroomName.setTextColor(Color.parseColor(it[1].text_color))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
                applyFont(it[1].font, requireContext().resources, binding.userGroomName)

                //binding.userDate.y = AppUtils.getActualDPsFromPixels1(requireContext(),it[2].com_Ypos.substringBefore(".").toInt())
                AppUtils.setMargins(
                    binding.userDate,
                    0,
                    AppUtils.convertPixelsToDp(it[2].com_Ypos.toFloat(), requireContext()).toInt(),
                    0,
                    0
                )
                if (businessPref.getANDate().isEmpty()) {
                    binding.userDate.text = resources.getText(R.string.party_date)
                    //binding.etDate.text = "12/08/2023"
                    //binding.etDate.setText(resources.getText(R.string.party_date))
                } else {
                    binding.userDate.text = businessPref.getANDate()
                    //binding.etDate.text = businessPref.getANDate()
                    binding.etDate.setText(businessPref.getANDate())
                }
                binding.userDate.textSize =
                    convertStringToFloat(it[2].text_size.toFloat(), requireContext().resources)
                try {
                    binding.userDate.setTextColor(Color.parseColor(it[2].text_color))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
                applyFont(it[2].font, requireContext().resources, binding.userDate)

                //binding.userContact.y = AppUtils.getActualDPsFromPixels1(requireContext(),it[3].com_Ypos.substringBefore(".").toInt())
                AppUtils.setMargins(
                    binding.userContact,
                    0,
                    AppUtils.convertPixelsToDp(it[3].com_Ypos.toFloat(), requireContext()).toInt(),
                    0,
                    0
                )
                if (businessPref.getANContact().isEmpty()) {
                    binding.userContact.text = resources.getText(R.string.mobile)
                    //binding.etContactNo.setText(resources.getText(R.string.mobile))
                } else {
                    binding.userContact.text = businessPref.getANContact()
                    binding.etContactNo.setText(businessPref.getANContact())
                }
                binding.userContact.textSize =
                    convertStringToFloat(it[3].text_size.toFloat(), requireContext().resources)
                try {
                    binding.userContact.setTextColor(Color.parseColor(it[3].text_color))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
                applyFont(it[3].font, requireContext().resources, binding.userContact)

                //binding.userAddress.y = AppUtils.getActualDPsFromPixels1(requireContext(),it[4].com_Ypos.substringBefore(".").toInt())
                AppUtils.setMargins(
                    binding.userAddress,
                    0,
                    AppUtils.convertPixelsToDp(it[4].com_Ypos.toFloat(), requireContext()).toInt(),
                    0,
                    0
                )
                if (businessPref.getANVenue().isEmpty()) {
                    binding.userAddress.text = resources.getText(R.string.b_party)
                    //binding.etVenue.setText(resources.getText(R.string.b_party))
                } else {
                    binding.userAddress.text = businessPref.getANVenue()
                    binding.etVenue.setText(businessPref.getANVenue())
                }
                binding.userAddress.textSize =
                    convertStringToFloat(it[4].text_size.toFloat(), requireContext().resources)
                try {
                    binding.userAddress.setTextColor(Color.parseColor(it[4].text_color))
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
                applyFont(it[4].font, requireContext().resources, binding.userAddress)
            }
        })
    }

    private fun buttonClick() {
        binding.ivCal.setOnClickListener {
            val dpd = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    month3 = "$dayOfMonth/${monthOfYear + 1}/$year"
                    //binding.etDate.text = month3
                    binding.etDate.setText(month3)
                },
                year,
                month1,
                day
            )
            dpd.show()
        }

//        binding.uploadLogo.setOnClickListener {
//            selectImage()
//        }

        binding.btnSubmit.setOnClickListener {
//            binding.userAddress.text = binding.etVenue.text
//            binding.userGroomName.text = binding.etGroomName.text
//            binding.userGroomFatherName.text = binding.etGroomFatherName.text
//            binding.userGroomMotherName.text = binding.etGroomMotherName.text
//            binding.userBrideName.text = binding.etBrideName.text
//            binding.userBrideFatherName.text = binding.etBrideFatherName.text
//            binding.userBrideMotherName.text = binding.etBrideMotherName.text
//            binding.userDate.text = binding.etDate.text
//            binding.userContact.text = binding.etContactNo.text
            businessPref.storeInvitationDetails(
                binding.etBrideName.text.toString(),
                binding.etGroomName.text.toString(),
                binding.etDate.text.toString(),
                binding.etContactNo.text.toString(),
                binding.etVenue.text.toString(),
                binding.etBrideFatherName.text.toString(),
                binding.etBrideMotherName.text.toString(),
                binding.etGroomFatherName.text.toString(),
                binding.etGroomMotherName.text.toString()
            )
            //binding.scrollView1.fullScroll(ScrollView.FOCUS_UP)
            val ivCardShareViewModel: IVCardShareViewModel by lazy {
                ViewModelProvider(
                    requireActivity()
                )[IVCardShareViewModel::class.java]
            }
            AppUtils.captureScreen1(binding.card2)
                ?.let { it1 -> ivCardShareViewModel.setImage(CardShareModel(it1)) }
            //findNavController().navigate(R.id.action_invitationEditFragment_to_cardPreviewFragment)
            AppUtils.changeFragmentNew(
                findNavController(),
                R.id.action_invitation_edit_frag_to_card_prev_frag,
                requireActivity()
            )
        }

//        binding.btnShare.setOnClickListener {
//            AppUtils.captureScreen1(binding.crd1)
//                ?.let { it1 -> AppUtils.shareBitmap(requireContext(), it1) }
//        }
    }

    private fun convertStringToFloat(input: Float, resources: Resources): Float {
        val mScreenDPI = resources.displayMetrics.density
        if (mScreenDPI <= 2.0) {
            return (input / context?.resources?.displayMetrics?.scaledDensity!!)
        } else {
            return (input / context?.resources?.displayMetrics?.scaledDensity!! + 4.0f)
        }
//        return (input / context?.resources?.displayMetrics?.scaledDensity!!+ 4.0f)
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

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun applyFont(tempString: String, resources: Resources, textView: TextView) {
//        val fontFields = R.font::class.java.fields
//        for (field in fontFields) {
//            try {
//                val str2 = tempString
//                val domain: String? = str2.substringBeforeLast(".")
//                if (field.name == domain) {
//                    val typeface = resources.getFont(field.getInt(null))
//                    textView.typeface = typeface
//                    val fName = field.name
//                    println("fieldName$fName")
//                }
//            } catch (e: Exception) {
////                e.printStackTrace()
//                Toast.makeText(requireContext(),"${e.printStackTrace()}",Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    fun applyFont(tempString: String, resources: Resources, textView: TextView) {
        val tf = Typeface.createFromAsset(requireContext().assets, tempString)
        textView.typeface = tf
    }

}