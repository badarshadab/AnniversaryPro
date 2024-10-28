package birthdaygreetings.birthdayframe.greetingswishes.ui.createCard

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Layout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.drawToBitmap
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.databinding.AddbgDiloglayoutBinding
import birthdaygreetings.birthdayframe.greetingswishes.databinding.AddtextDialogLayBinding
import birthdaygreetings.birthdayframe.greetingswishes.databinding.FragmentCreateCardFGBinding
import birthdaygreetings.birthdayframe.greetingswishes.databinding.ImgdialogBinding
import birthdaygreetings.birthdayframe.greetingswishes.databinding.QuoteslistdlayoutBinding
import birthdaygreetings.birthdayframe.greetingswishes.databinding.StickerdiloglayoutBinding
import birthdaygreetings.birthdayframe.greetingswishes.ui.createCard.adapter.AddbgCardAdapter
import birthdaygreetings.birthdayframe.greetingswishes.ui.createCard.adapter.ChooseColorAdapter
import birthdaygreetings.birthdayframe.greetingswishes.ui.createCard.adapter.ChooseGradCloreAdapter
import birthdaygreetings.birthdayframe.greetingswishes.ui.createCard.adapter.FontsAdapter
import birthdaygreetings.birthdayframe.greetingswishes.ui.createCard.adapter.QuotesAdapter
import birthdaygreetings.birthdayframe.greetingswishes.ui.createCard.adapter.StickerAdpter
import birthdaygreetings.birthdayframe.greetingswishes.ui.createCard.adapter.TextChooseColorAdapter
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.MultiTouchListener
import birthdaygreetings.birthdayframe.greetingswishes.util.OnItemClickListener
import birthdaygreetings.birthdayframe.greetingswishes.util.OnItemClickListener_Quotes
import birthdaygreetings.birthdayframe.greetingswishes.util.StickerImageView
import birthdaygreetings.birthdayframe.greetingswishes.util.StickerOnItemClick
import birthdaygreetings.birthdayframe.greetingswishes.util.TextEditerDilogViewModel
import birthdaygreetings.birthdayframe.greetingswishes.util.costomtextediter.TextSticker
import birthdaygreetings.birthdayframe.greetingswishes.util.costomtextediter.TextStickerView
import birthdaygreetings.birthdayframe.greetingswishes.viewmodel.CreateCardViewModel
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.skydoves.colorpickerview.AlphaTileView
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.flag.BubbleFlag
import com.skydoves.colorpickerview.flag.FlagMode
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import com.skydoves.colorpickerview.sliders.AlphaSlideBar
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CreateCardFG : Fragment(), MenuProvider, View.OnClickListener {


    private var flagimg: String? = null


    lateinit var textEditerDilogViewModel: TextEditerDilogViewModel
    var colorcode: Int = 0
    var fontstype: Int = 0


    private var addtextbinding: AddtextDialogLayBinding? = null
    lateinit var fontdata: Typeface
    var texteditcolor: Int = 0


    private var addbgdialog: Dialog? = null
    private var addbgcolordialog: Dialog? = null
    private var addtextdialog: Dialog? = null
    private var quotelistdialog: Dialog? = null
    private var colorpickdialog: Dialog? = null
    private var stickerdialog: Dialog? = null


    var path: String = ""
    private var param2: String? = null
    private var catName: String? = null

    private lateinit var createCardViewModel: CreateCardViewModel
    var aalphaTileview: AlphaTileView? = null
    lateinit var _binding: FragmentCreateCardFGBinding
    private var addbgbinding: AddbgDiloglayoutBinding? = null
    lateinit var quotesbinding: QuoteslistdlayoutBinding
    lateinit var stickerBinding: StickerdiloglayoutBinding

    val binding get() = _binding


    lateinit var textStickerView1: TextStickerView
    lateinit var textStickerView2: TextStickerView
    lateinit var textStickerView3: TextStickerView

    lateinit var textSticker1: TextSticker
    lateinit var textSticker2: TextSticker
    lateinit var textSticker3: TextSticker
    private var inputFake: EditText? = null

    lateinit var stickerImageView: StickerImageView
    lateinit var activity: Activity

    var isTextEntered = false
    var isBgAdded = false


    var title = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            catName = it.getString("catName")
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as Activity
//        https://stackoverflow.com/questions/28672883/java-lang-illegalstateexception-fragment-not-attached-to-activity
//        Fragment ContentPreviewFragment{fb22d83} (743b8906-1fa7-4828-8024-cc60ff8aac63) not attached to an activity.

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = title
        super.onViewCreated(view, savedInstanceState)
        println("Shadab flow onViewCreated")
//        val callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                if (isTextEntered || isBgAdded) {
//                    saveTheChanges()
//                } else {
//                    findNavController().popBackStack()
//                }
//            }
//        }

//        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateCardFGBinding.inflate(layoutInflater, container, false)
        createCardViewModel = ViewModelProvider(this)[CreateCardViewModel::class.java]
        createCardViewModel.colorList()
        createCardViewModel.fontList()
        createCardViewModel.gradientList()
        title = arguments?.getString("title").toString()

        isBgAdded = false
        isTextEntered = false
        println("Shadab flow onCreate")

        AdUtils.showAdaptiveBanner(requireActivity(), binding.addid)
        path = "CreateCards/$catName"
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
//        setMenu()
        textSticker1 =
            TextSticker(requireContext())
        textSticker2 =
            TextSticker(requireContext())
        textSticker3 =
            TextSticker(requireContext())
        textStickerView1 = TextStickerView(requireContext())
        textStickerView1.isConstrained = true
        textStickerView1.isLocked = false
        textStickerView2 =
            TextStickerView(requireContext())
        textStickerView2.isConstrained = true
        textStickerView2.isLocked = false
        textStickerView3 =
            TextStickerView(requireContext())
        textStickerView3.isConstrained = true
        textStickerView3.isLocked = false

        inputFake = binding.drawingInputFake
        inputFake?.isCursorVisible = false
        inputFake?.doAfterTextChanged {
            textStickerView1.text = it?.toString()
            textStickerView2.text = it?.toString()
            textStickerView3.text = it?.toString()
        }
        binding.addbgid.setOnClickListener(this)
        binding.addtextid.setOnClickListener(this)
        binding.quoteid.setOnClickListener(this)
        binding.fontsid.setOnClickListener(this)
        binding.textcolorid.setOnClickListener(this)
        binding.stickersid.setOnClickListener(this)
        binding.create.setOnClickListener(this)

        textEditerDilogViewModel = ViewModelProvider(this)[TextEditerDilogViewModel::class.java]


        return binding.root
    }

    private fun addbgDilog() {
        addbgbinding = AddbgDiloglayoutBinding.inflate(layoutInflater)
        addbgdialog = BottomSheetDialog(
            requireActivity(),
            R.style.CustomBottomSheetDialogTheme
        ) //, R.style.addbgWideDialog
        addbgdialog?.setContentView(addbgbinding!!.root)
        addbgdialog?.setCancelable(true)
        addbgdialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addbgdialog?.window?.setGravity(Gravity.BOTTOM)
        addbgbinding?.addbggoodmg?.text = catName?.capitalize()
//        SmAdds.showBannerAdd(requireActivity(), addbgbinding!!.bottomaddscbackid)
        addbgbinding?.colorid?.setOnClickListener {
            colorDilog()
            addbgdialog?.dismiss()
        }
        addbgbinding?.galleryid?.setOnClickListener {
            binding.create.visibility = View.GONE
            binding.createimageview.setOnTouchListener(MultiTouchListener())
            selectImageFromGallery()
            addbgdialog?.dismiss()
        }
        addbgbinding?.cameraid?.setOnClickListener {
            val mListener = MultiTouchListener()
            mListener.minimumScale = 0.1f
            binding.createimageview.setOnTouchListener(mListener)
            takeImage()
            addbgdialog?.dismiss()
        }
        println("path $path")
        addbgbinding?.addbgrecycleid.apply {
            createCardViewModel.getALLGif(path)
                ?.observe(requireActivity(), androidx.lifecycle.Observer { datalist ->
                    val addbgCardAdapter = AddbgCardAdapter(datalist, requireContext(), object :
                        OnItemClickListener {
                        override fun onClick(position: Int) {
                            isBgAdded = true
                            binding.create.visibility = View.GONE
                            Glide.with(requireContext()).load(datalist[position])
                                .thumbnail(
                                    Glide.with(requireContext()).load(R.drawable.loading_img)
                                )
                                .error(R.drawable.error)
                                .into(binding.createimageview)
//                        binding.nextBtn.visibility = View.VISIBLE
                            addbgdialog?.dismiss()
                        }
                    })

                    this?.adapter = addbgCardAdapter
                    addbgbinding?.bgprogressbarid?.progresbarid?.visibility = View.GONE
                })
        }
        addbgdialog?.show()
    }

    private fun colorDilog() {
        val colordilogslayout = layoutInflater.inflate(R.layout.addbg_colorlayout, null)
        addbgcolordialog =
            BottomSheetDialog(requireActivity(), R.style.CustomBottomSheetDialogTheme)
        addbgcolordialog?.setContentView(colordilogslayout)
        addbgcolordialog?.setCancelable(true)
        addbgcolordialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addbgcolordialog?.window?.setGravity(Gravity.BOTTOM)
        var dilogrecycler1: RecyclerView = colordilogslayout.findViewById(R.id.onecolorrecyView)
        val coloranim: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_ups)
        dilogrecycler1.startAnimation(coloranim)
        createCardViewModel.colorlivedata.observe(
            requireActivity(),
            androidx.lifecycle.Observer { bgcolorlist ->
                val colorAdapter = ChooseColorAdapter(
                    bgcolorlist,
                    requireContext(),
                    object : OnItemClickListener {
                        override fun onClick(position: Int) {
                            isBgAdded = true
                            if (position == 0) {
//                                binding.nextBtn.visibility = View.VISIBLE
//                                pickDilog()
                                addbgcolordialog?.dismiss()
                            } else {
                                binding.createimageview.setImageDrawable(null)
                                binding.createimageview.setBackgroundColor(bgcolorlist[position].color)
//                                binding.nextBtn.visibility = View.VISIBLE
                                addbgcolordialog?.dismiss()
                            }
                        }
                    })
                dilogrecycler1.adapter = colorAdapter

            })
        val dilogrecycler3: RecyclerView = colordilogslayout.findViewById(R.id.gridentrecycView)
        dilogrecycler3.startAnimation(coloranim)
        dilogrecycler3.apply {
            createCardViewModel.gradientlivedata.observe(requireActivity(),
                androidx.lifecycle.Observer { gradinentdatalist ->
                    val chooseGradCloreAdapter =
                        ChooseGradCloreAdapter(gradinentdatalist, requireContext(),
                            object : OnItemClickListener {
                                override fun onClick(position: Int) {
                                    binding.createimageview.setImageDrawable(null)
                                    val gd = GradientDrawable(
                                        GradientDrawable.Orientation.TOP_BOTTOM,
                                        gradinentdatalist[position].gradlist
                                    )
                                    gd.cornerRadius = 0f
                                    binding.createimageview.setBackgroundDrawable(gd)
//                                binding.nextBtn.visibility = View.VISIBLE
                                    addbgcolordialog?.dismiss()
                                }
                            })
                    adapter = chooseGradCloreAdapter
                })
        }
        addbgcolordialog?.show()
    }

    private fun addTextDilog() {
        val addtextlayout = layoutInflater.inflate(R.layout.addtext, null)
        addtextdialog = BottomSheetDialog(requireActivity(), R.style.CustomBottomSheetDialogTheme)
        addtextdialog?.setContentView(addtextlayout)
        addtextdialog?.window?.setGravity(Gravity.BOTTOM)
        addtextdialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        addtextdialog?.setCancelable(true)
        addtextdialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        var cancelbtn: TextView = addtextlayout.findViewById(R.id.cancel_btn)
//        var vancelbtn2: ImageView = addtextlayout.findViewById(R.id.canclebtn2)
        var addtext: EditText = addtextlayout.findViewById(R.id.textid)
        var okbtn: TextView = addtextlayout.findViewById(R.id.addokbtn)
        cancelbtn.setOnClickListener {
            addtextdialog?.dismiss()
        }
        okbtn.setOnClickListener {
            if (addtext.text.toString().trim().isEmpty()) {
                addtext.hint = "Please Enter Text"
            } else {
                textSticker1.text = addtext.text.toString()
                textStickerView1.addSticker(textSticker1)
                textSticker1.setTextColor(Color.BLACK)
                textSticker1.setTextAlign(Layout.Alignment.ALIGN_CENTER)
                textSticker1.resizeText()
                binding.cardsharesaveid.removeView(textStickerView1)
                binding.cardsharesaveid.addView(textStickerView1)
//                binding.nextBtn.visibility = View.VISIBLE
                isTextEntered = true
                binding.create.visibility = View.GONE
                addtextdialog?.dismiss()
            }
        }
        /* vancelbtn2.setOnClickListener {
             addtextdialog?.dismiss()
         }*/
        addtextdialog?.show()
    }


    private fun addTextDilog1() {
        addtextbinding = AddtextDialogLayBinding.inflate(layoutInflater)
        addtextdialog = Dialog(requireContext(), R.style.WideDialog)
        addtextdialog?.window?.attributes?.windowAnimations = R.style.DialogTheme2

        addtextdialog?.setContentView(addtextbinding!!.root)
        addtextdialog?.setCancelable(true)
        addtextdialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        fontdata = Typeface.createFromAsset(requireContext().assets, "1.ttf")
        texteditcolor = Color.parseColor("#FF000000")

        addtextbinding?.cancelBtn?.setOnClickListener {
            addtextdialog?.dismiss()
        }
        addtextbinding?.addokbtn?.setOnClickListener {

            var msgToAdd = addtextbinding!!.textid.text.toString()
            val triple = Triple(msgToAdd, texteditcolor, fontdata)
            AppUtils.addStringToView(binding.cardsharesaveid, requireContext(), triple)
            isTextEntered = true
            binding.create.visibility = View.GONE
            addtextdialog?.dismiss()
        }


        addtextdialog?.show()
    }

    private fun quotesListDilog() {
        quotesbinding = QuoteslistdlayoutBinding.inflate(layoutInflater)
        quotelistdialog = BottomSheetDialog(requireActivity(), R.style.CustomBottomSheetDialogTheme)
        quotelistdialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        quotelistdialog?.setContentView(quotesbinding.root)
        quotelistdialog?.setCancelable(true)
        quotelistdialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        quotelistdialog?.window?.setGravity(Gravity.BOTTOM)
//        SmAdds.showNativeBannerAdd(requireActivity(), quotesbinding.bottomaddsccard)
        quotesbinding.recyclerquote.apply {
            createCardViewModel.getData("$path/Quotes")
                .observe(requireActivity(), androidx.lifecycle.Observer { quotedata ->
//                    println("Mycompe path ${quotedata}")
                    var quotesAdapter = QuotesAdapter(
                        quotedata,
                        requireContext(),
                        object : OnItemClickListener_Quotes {
                            override fun onClick(position: Int) {
                                textSticker2.text = quotedata[position]
                                textStickerView2.addSticker(textSticker2)
                                textSticker2.setTextColor(Color.BLACK)
                                textSticker2.setTextAlign(Layout.Alignment.ALIGN_CENTER)
                                textSticker2.resizeText()
                                binding.cardsharesaveid.removeView(textStickerView2)
                                binding.cardsharesaveid.addView(textStickerView2)
                                isTextEntered = true
                                binding.create.visibility = View.GONE
//                                binding.nextBtn.visibility = View.VISIBLE
                                quotelistdialog?.dismiss()


//                                fontdata = Typeface.createFromAsset(requireContext().assets, "1.ttf")
//                                texteditcolor = Color.parseColor("#FF000000")
//                                var msgToAdd = quotedata[position]
//                                val triple = Triple(msgToAdd, texteditcolor, fontdata)
//                                AppUtils.addStringToView(binding.cardsharesaveid, requireContext(), triple)
//                                isTextEntered = true
//                                binding.create.visibility = View.GONE
//                                quotelistdialog?.dismiss()

                            }
                        })
                    adapter = quotesAdapter
                    quotesbinding.quoteprocessid.progresbarid.visibility = View.GONE
                })
        }

        quotelistdialog?.show()
    }

    private fun fontListDilog() {

        if (isTextEntered) {

//        binding.toolbartitle.setText(R.string.fonts)
            binding.fontcolorlistlayout.visibility = View.VISIBLE
            binding.fontcolorlistlayout.setBackgroundColor(Color.WHITE)
            val anim: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_ups)
            binding.fontcolorlistlayout.startAnimation(anim)
            binding.listfontcolorrecyclerview.apply {
                createCardViewModel.fontlivedata.observe(
                    requireActivity(),
                    androidx.lifecycle.Observer { fontdatalist ->
                        val fontsAdapter = FontsAdapter(
                            fontdatalist,
                            requireActivity(),
                            object : OnItemClickListener {
                                override fun onClick(position: Int) {
                                    val fontstyle2 = fontdatalist[position].fontstyle?.let { it1 ->
                                        ResourcesCompat.getFont(requireActivity(), it1)
                                    }
                                    textSticker1.setTypeface(fontstyle2)
                                    textSticker2.setTypeface(fontstyle2)
                                }
                            })
                        adapter = fontsAdapter
                    })
            }
        } else {
            Toast.makeText(requireContext(), "Please enter Some text first", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun TextColorListDilog() {

        println("textSticker2.text " + textSticker2.text)
        if (isTextEntered) {
            //        binding.toolbartitle.setText(R.string.text_color)
//        binding.fontcolorlistlayout.visibility = View.VISIBLE
            binding.fontcolorlistlayout.setBackgroundColor(Color.WHITE)
            val anim: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_left)
            binding.fontcolorlistlayout.startAnimation(anim)
            binding.listfontcolorrecyclerview.apply {
                createCardViewModel.colorlivedata.observe(
                    requireActivity(),
                    androidx.lifecycle.Observer { fonttextcolor ->
                        val textAdapter = TextChooseColorAdapter(
                            fonttextcolor,
                            requireActivity(),
                            object : OnItemClickListener {
                                override fun onClick(position: Int) {
                                    if (position == 0) {
                                        textPickDilog()
                                    } else {
                                        textSticker1.setTextColor(fonttextcolor[position].color)
                                        textSticker2.setTextColor(fonttextcolor[position].color)
                                        textSticker3.setTextColor(fonttextcolor[position].color)

                                        /*dtextView?.setTextColor(fonttextcolor[position].color)
                                        quotetext?.setTextColor(fonttextcolor[position].color)*/
                                    }
                                }
                            })
                        adapter = textAdapter

                    })
            }
        } else {
            Toast.makeText(requireContext(), "Please enter Some text first", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun textPickDilog() {
        val colorpiclayout = layoutInflater.inflate(R.layout.colorpicklayout, null)
        colorpickdialog = Dialog(requireContext(), R.style.WideDialog)
        colorpickdialog?.setContentView(colorpiclayout)
        colorpickdialog?.setCancelable(true)
        colorpickdialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        colorpickdialog?.window?.setGravity(Gravity.CENTER_HORIZONTAL)


        val cancelbtn: ImageView = colorpiclayout.findViewById(R.id.cancelbtnid)
        val colorpickview: ColorPickerView = colorpiclayout.findViewById(R.id.colorPickerView)
        val alphaSlidebar: AlphaSlideBar = colorpiclayout.findViewById(R.id.alphaSlideBar)
        val brightnessslide: BrightnessSlideBar = colorpiclayout.findViewById(R.id.brightnessSlide)
        aalphaTileview = colorpiclayout.findViewById(R.id.alphatileView)
        val done_btn: TextView = colorpiclayout.findViewById(R.id.okid)
        var seekbarid: SeekBar = colorpiclayout.findViewById(R.id.seekbar)

        cancelbtn.setOnClickListener {
            colorpickdialog?.dismiss()
        }

        val bubbleFlag = BubbleFlag(requireContext())
        bubbleFlag.flagMode = FlagMode.ALWAYS
        var envelope2: ColorEnvelope? = null
        colorpickview.flagView = bubbleFlag
        colorpickview.setColorListener(
            ColorEnvelopeListener { envelope: ColorEnvelope, fromUser: Boolean ->
                envelope2 = envelope

                aalphaTileview?.setPaintColor(envelope.color)
                Timber.d("color: %s", envelope.hexCode)
//                        percenTage.text = setColorAlpha(100,"#" + envelope.hexCode)


            })

        colorpickview.attachAlphaSlider(alphaSlidebar)
        colorpickview.attachBrightnessSlider(brightnessslide)
        colorpickview.setLifecycleOwner(this)





        done_btn.setOnClickListener {

            binding.create.visibility = View.GONE
            envelope2?.let { it1 -> textSticker1.setTextColor(it1.color) }
            envelope2?.let { it1 -> textSticker2.setTextColor(it1.color) }
            /* textSticker3.setTextColor(it[position].color)
             dtextView?.setTextColor(envelope2!!.color)
             quotetext?.setTextColor(envelope2!!.color)*/

            colorpickdialog?.dismiss()
        }



        seekbarid.visibility = View.GONE
        seekbarid.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        colorpickdialog?.show()
    }

    private fun stickersDilog() {
        if (isBgAdded) {

            stickerBinding = StickerdiloglayoutBinding.inflate(layoutInflater)
            stickerdialog =
                BottomSheetDialog(requireActivity(), R.style.CustomBottomSheetDialogTheme)
            stickerdialog?.setContentView(stickerBinding.root)
            stickerdialog?.setCancelable(true)
            stickerdialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            stickerdialog?.window?.setGravity(Gravity.BOTTOM)
//        stickerdialog?.getWindow()?.getAttributes()?.windowAnimations = R.style.DialogTheme4;
//        SmAdds.showBannerAdd(requireActivity(), stickerBinding.bottomaddscstickerid)
            stickerBinding.stickerrecycleid.apply {
                createCardViewModel.getALLSticker("$path").observe(
                    requireActivity(),
                    androidx.lifecycle.Observer { datalist ->

                        val stickerAdpter =
                            StickerAdpter(
                                datalist,
                                requireContext(),
                                object : StickerOnItemClick {
                                    override fun onClick(view: View, position: Int) {

                                        stickerImageView =
                                            StickerImageView(
                                                requireActivity()
                                            )
                                        stickerImageView.imageBitmap = view?.drawToBitmap()

                                        binding.cardrootlayout.addView(stickerImageView)
                                        stickerdialog?.dismiss()

                                    }

                                })

                        adapter = stickerAdpter
                        stickerBinding.backgroundpbarid.progresbarid.visibility = View.GONE


                    })
            }
            stickerdialog?.show()
        } else {
            Toast.makeText(requireContext(), "Please apply any BG first", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.addbgid -> {
//                binding.addtextid.setCompoundDrawablesWithIntrinsicBounds(null, R.drawable.addtext_un, 0, null, null)

                binding.listfontcolorrecyclerview.visibility = View.GONE
                changeDrawableTop(binding.addbgid, R.drawable.addbg_un, 1)
                changeDrawableTop(binding.addtextid, R.drawable.addtext, 0)
                changeDrawableTop(binding.quoteid, R.drawable.quotesi, 0)
                changeDrawableTop(binding.fontsid, R.drawable.fonts, 0)
                changeDrawableTop(binding.textcolorid, R.drawable.textcolor, 0)
                changeDrawableTop(binding.stickersid, R.drawable.stickers, 0)


                addbgDilog()

            }

            binding.addtextid -> {
                binding.listfontcolorrecyclerview.visibility = View.GONE
                changeDrawableTop(binding.addtextid, R.drawable.addtext_un, 1)
                changeDrawableTop(binding.addbgid, R.drawable.addbg, 0)
                changeDrawableTop(binding.quoteid, R.drawable.quotesi, 0)
                changeDrawableTop(binding.fontsid, R.drawable.fonts, 0)
                changeDrawableTop(binding.textcolorid, R.drawable.textcolor, 0)
                changeDrawableTop(binding.stickersid, R.drawable.stickers, 0)

                addTextDilog1()
            }

            binding.quoteid -> {
                binding.listfontcolorrecyclerview.visibility = View.GONE

                changeDrawableTop(binding.quoteid, R.drawable.quotes_un, 1)
                changeDrawableTop(binding.addbgid, R.drawable.addbg, 0)
                changeDrawableTop(binding.addtextid, R.drawable.addtext, 0)
                changeDrawableTop(binding.fontsid, R.drawable.fonts, 0)
                changeDrawableTop(binding.textcolorid, R.drawable.textcolor, 0)
                changeDrawableTop(binding.stickersid, R.drawable.stickers, 0)

                quotesListDilog()
            }

            binding.fontsid -> {
                binding.listfontcolorrecyclerview.visibility = View.VISIBLE

                changeDrawableTop(binding.fontsid, R.drawable.fonts_un, 1)
                changeDrawableTop(binding.addbgid, R.drawable.addbg, 0)
                changeDrawableTop(binding.addtextid, R.drawable.addtext, 0)
                changeDrawableTop(binding.quoteid, R.drawable.quotesi, 0)
                changeDrawableTop(binding.textcolorid, R.drawable.textcolor, 0)
                changeDrawableTop(binding.stickersid, R.drawable.stickers, 0)

                fontListDilog()
            }

            binding.textcolorid -> {
                binding.listfontcolorrecyclerview.visibility = View.VISIBLE

                changeDrawableTop(binding.textcolorid, R.drawable.textcolor_un, 1)
                changeDrawableTop(binding.addbgid, R.drawable.addbg, 0)
                changeDrawableTop(binding.addtextid, R.drawable.addtext, 0)
                changeDrawableTop(binding.quoteid, R.drawable.quotesi, 0)
                changeDrawableTop(binding.fontsid, R.drawable.fonts, 0)
                changeDrawableTop(binding.stickersid, R.drawable.stickers, 0)



                TextColorListDilog()
            }

            binding.stickersid -> {
                binding.listfontcolorrecyclerview.visibility = View.GONE

                changeDrawableTop(binding.stickersid, R.drawable.stickers_un, 1)
                changeDrawableTop(binding.addbgid, R.drawable.addbg, 0)
                changeDrawableTop(binding.addtextid, R.drawable.addtext, 0)
                changeDrawableTop(binding.quoteid, R.drawable.quotesi, 0)
                changeDrawableTop(binding.fontsid, R.drawable.fonts, 0)
                changeDrawableTop(binding.textcolorid, R.drawable.textcolor, 0)


                stickersDilog()
            }


            binding.create -> {
                binding.listfontcolorrecyclerview.visibility = View.GONE
                changeDrawableTop(binding.addbgid, R.drawable.addbg, 1)
                changeDrawableTop(binding.addtextid, R.drawable.addtext_un, 0)
                changeDrawableTop(binding.quoteid, R.drawable.quotes_un, 0)
                changeDrawableTop(binding.fontsid, R.drawable.fonts_un, 0)
                changeDrawableTop(binding.textcolorid, R.drawable.textcolor_un, 0)
                changeDrawableTop(binding.stickersid, R.drawable.stickers_un, 0)


                addbgDilog()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun myimgDialog() {
        val binddilog = ImgdialogBinding.inflate(layoutInflater)
        val dialog1 = Dialog(requireContext())
        dialog1.setCancelable(true)
        dialog1.setCanceledOnTouchOutside(false)
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog1.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog1.setContentView(binddilog.root)
        dialog1.show()

        binddilog.cameraBtn.setOnClickListener {
            flagimg = "IMGSET"
            takeImage()
            dialog1.dismiss()
        }
        binddilog.canclebtn.setOnClickListener {
            dialog1.dismiss()
        }
        binddilog.galleryBtn.setOnClickListener {
            flagimg = "IMGSET"
            selectImageFromGallery()
            dialog1.dismiss()
        }


    }

    // GALLERY
    private val selectImageFromGalleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                if (flagimg == "IMGSET") {
                    stickerImageView =
                        StickerImageView(requireActivity())
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
                    stickerImageView.imageBitmap = bitmap
//                            val photo = data.get("data") as Bitmap
                    binding.cardrootlayout.addView(stickerImageView)
                    flagimg = ""

                } else {
                    binding.createimageview.setImageURI(uri)
                }

//            binding.nextBtn.visibility = View.VISIBLE
            }
        }

    private fun selectImageFromGallery() = selectImageFromGalleryResult.launch("image/*")

    // CAMERA
    private var latestTmpUri: Uri? = null
    private fun takeImage() {
        lifecycleScope.launchWhenStarted {
            getTmpFileUri().let { uri ->
                latestTmpUri = uri
                takeImageResult.launch(uri)

            }
        }
    }

    private fun getTmpFileUri(): Uri {
        val tmpFile =
            File.createTempFile("tmp_image_file", ".png", requireActivity().cacheDir).apply {
                createNewFile()
                deleteOnExit()
            }
        return FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            tmpFile
        )
    }

    private val takeImageResult =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                latestTmpUri?.let { uri ->
                    if (flagimg == "IMGSET") {
                        stickerImageView =
                            StickerImageView(requireActivity())
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            requireActivity().contentResolver,
                            uri
                        )
                        stickerImageView.imageBitmap = bitmap
//                            val photo = data.get("data") as Bitmap
                        binding.cardrootlayout.addView(stickerImageView)
                        flagimg = ""

                    } else {
                        binding.createimageview.setImageURI(uri)
                    }

//                binding.nextBtn.visibility = View.VISIBLE
                }
            }
        }


    fun saveBitmap(bitmap: Bitmap): String? {
        var fileName: String? = "ImageName" //no .png or .jpg needed
        try {
            val bytes = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val fo: FileOutputStream =
                requireActivity().openFileOutput(fileName, Context.MODE_PRIVATE)
            fo.write(bytes.toByteArray())
            // remember close file output
            fo.close()
        } catch (e: Exception) {
            e.printStackTrace()
            fileName = null
        }
        return fileName
    }


    private fun changeDrawableTop(textView: TextView, drawableId: Int, colorCode: Int) {
        val drawable: Drawable? = AppCompatResources.getDrawable(textView.context, drawableId)
        // Set the drawable as the top drawable for the TextView
        textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
        if (colorCode == 0) {
            textView.setTextColor(resources.getColor(R.color.white))
        } else {
            textView.setTextColor(resources.getColor(R.color.unselect_col_create_cards))
        }

    }


    fun saveTheChanges() {
        var dialog = Dialog(activity)
        val appexitlayout = activity.layoutInflater.inflate(R.layout.save_changes, null)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(appexitlayout)
        val lout = WindowManager.LayoutParams()
        lout.copyFrom(dialog.window?.attributes)
        lout.width = WindowManager.LayoutParams.MATCH_PARENT
        lout.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog.setCancelable(true)
        AdUtils.showMedRect(requireActivity(), appexitlayout.findViewById(R.id.exitscreenadd))
        dialog.window?.setAttributes(lout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        dialog.window?.statusBarColor =
            ContextCompat.getColor(activity, R.color.app_backgroudncolor)
        var discardBtn: TextView = appexitlayout.findViewById(R.id.discard)
        var cancelBtn: TextView = appexitlayout.findViewById(R.id.cancel_save)

        cancelBtn.setOnClickListener { view: View? ->
            isTextEntered = false
            isBgAdded = false
            dialog.dismiss()
        }
        discardBtn.setOnClickListener {
            dialog.dismiss()
            isTextEntered = false
            isBgAdded = false
            findNavController().popBackStack()
        }
        dialog.show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.nextbtnid -> {

                var viewdata = AppUtils.getBitmapFromView(binding.cardsharesaveid)
                var bundle = Bundle()
                bundle.putString("bitimgkey", saveBitmap(viewdata))
                if (isTextEntered || isBgAdded) {
                    AppUtils.changeFragmentWithPosition(
                        findNavController(),
                        R.id.action_create_card_fg_to_create_card_preview,
                        requireActivity(),
                        bundle
                    )
                } else {
                    Toast.makeText(requireContext(), "Set Image Or Text First", Toast.LENGTH_SHORT)
                        .show()
                }
                true

            }

            else -> false
        }
    }


}