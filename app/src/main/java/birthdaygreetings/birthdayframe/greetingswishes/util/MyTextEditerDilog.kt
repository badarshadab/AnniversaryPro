package birthdaygreetings.birthdayframe.greetingswishes.util

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.card.MaterialCardView
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.adapter.ChooseColorAdapter
import birthdaygreetings.birthdayframe.greetingswishes.adapter.FontsAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.TexteditordialogBinding

class MyTextEditerDilog(
    val myquote: String,
    val bubbleTextView: birthdaygreetings.birthdayframe.greetingswishes.util.BubbleTextView,
    val container: MaterialCardView
) : DialogFragment() {

    lateinit var myedittext: String
    var myqotesdata: String = ""

    companion object {
        var mystring: String = ""
    }

    var myedittexts: String = ""
    var colorcode: Int = 0
    var fontstype: Int = 0

    lateinit var textEditerDilogViewModel: TextEditerDilogViewModel
    override fun getTheme(): Int = R.style.NoMarginsDialog
    lateinit var binding: TexteditordialogBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TexteditordialogBinding.inflate(layoutInflater, container, false)
        setupWidthToMatchParent()
        textEditerDilogViewModel = ViewModelProvider(this)[TextEditerDilogViewModel::class.java]

        binding.edittextid.setText(myquote, TextView.BufferType.EDITABLE)
        textEditerDilogViewModel.colorList()
        textEditerDilogViewModel.colorlivedata.observe(this) { colordata ->
            var chooseColorAdapter =
                ChooseColorAdapter(colordata, requireContext(), object : OnItemClickListener {
                    override fun onClick(position: Int) {
                        binding.edittextid.setTextColor(colordata[position].color)
                        colorcode = colordata[position].color
                    }
                })
            binding.colordilaogid.adapter = chooseColorAdapter

        }
        textEditerDilogViewModel.fontList()
        textEditerDilogViewModel.fontlivedata.observe(this) { fontlist ->
            binding.closeid.setOnClickListener {
                dialog?.cancel()
            }
            var fontsAdapter =
                FontsAdapter(fontlist, requireContext(), object : OnItemClickListener {
                    override fun onClick(position: Int) {
                        val face3 = ResourcesCompat.getFont(
                            requireContext(),
                            fontlist[position].fontstyle!!
                        )
                        binding.edittextid.setTypeface(face3)
                        fontstype = fontlist[position].fontstyle!!
                    }
                })
            binding.fontrecyclerview.adapter = fontsAdapter
        }

        binding.doneid.setOnClickListener {

//                       myedittexts =

//                    val face3 = ResourcesCompat.getFont(requireContext(), fontstype)

//                      val triple = Triple( binding.edittextid.text.toString().trim() ,colorcode, face);

            if (binding.edittextid.text.toString().trim().isEmpty()) {
//                Toast.makeText(
//                    requireContext(),
//                    "" + binding.edittextid.text.toString().trim().isEmpty(),
//                    Toast.LENGTH_SHORT
//                ).show()
                dialog?.cancel()
            } else {
                var typeface = Typeface.createFromAsset(view?.context!!.assets, "1.ttf")
                binding.edittextid.setTextColor(Color.parseColor("#FF000000"))
                if (colorcode == 0) {
                    colorcode = bubbleTextView.fontColor
                }
                if (fontstype == 0) {
                    typeface = bubbleTextView.typeface
                } else {
                    typeface = ResourcesCompat.getFont(requireContext(), fontstype)
                }
//                       if()
                val triple = Triple(binding.edittextid.text.toString(), colorcode, typeface)

                addStringToView(requireContext(), triple)

            }

            dialog?.cancel()

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCancelable(isCancelable)
    }

    private fun addStringToView(context: Context, triple: Triple<String, Int, Typeface>) {
        val string =
            triple.first//        var  tv_sticker = BubbleTextView(context, triple.second, triple.third, 0,string)
        bubbleTextView.setOperationListener(object : BubbleTextView.OperationListener {
            override fun onDeleteClick() {
                removeAddedView(bubbleTextView)
            }

            override fun onEdit(bubbleTextView: BubbleTextView?) {
                bubbleTextView.let {
                    val onEdit = !bubbleTextView?.isInEditMode!!
                    bubbleTextView.setInEdit(onEdit)
                }
            }

            override fun onClick(bubbleTextView: BubbleTextView?) {
            }

            override fun onTop(bubbleTextView: birthdaygreetings.birthdayframe.greetingswishes.util.BubbleTextView?) {
            }
        })
        if (string.length <= 200) {
            bubbleTextView.setImageResource(R.mipmap.bubble_7_rb_250)
        } else if (string.length > 200 && string.length < 400) {
            bubbleTextView.setImageResource(R.mipmap.bubble_7_rb_100)
        } else if (string.length >= 400 && string.length < 800) {
            bubbleTextView.setImageResource(R.mipmap.bubble_7_rb_500_200)
        } else {
            bubbleTextView.setImageResource(R.mipmap.bubble_7_rb)
        }
        bubbleTextView.setColorNFont(triple.first, triple.second, triple.third)
//        bubbleTextView.setText(string)
        addMovableItemOnView(bubbleTextView)
//        addMovableItemOnView(bubbleTextView)
//        addMovableItemOnView(bubbleTextView)
//        addMovableItemOnView(bubbleTextView)
    }

    private fun addMovableItemOnView(any: View) {
        removeAddedView(any)
        container.addView(any)
    }

    private fun removeAddedView(view: View) {
        container.removeView(view)
    }

    fun DialogFragment.setupWidthToMatchParent() {
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}