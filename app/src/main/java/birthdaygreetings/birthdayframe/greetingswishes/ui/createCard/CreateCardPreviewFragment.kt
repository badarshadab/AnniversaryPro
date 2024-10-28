package birthdaygreetings.birthdayframe.greetingswishes.ui.createCard

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import birthdaygreetings.birthdayframe.greetingswishes.databinding.FragmentCreateCardPreviewBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import java.io.FileNotFoundException

private const val ARG_PARAM1 = "bitimgkey"
private const val ARG_PARAM2 = "param2"


class CreateCardPreviewFragment : Fragment() {
    lateinit var _binding: FragmentCreateCardPreviewBinding
    val binding get() = _binding
    private var param1: String? = null
    private var param2: String? = null
    var src: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateCardPreviewBinding.inflate(layoutInflater, container, false)
//        binding.toolbarid.activitytitle.setText(R.string.createcard)
        AdUtils.showMedRect(requireActivity(), binding.bottomaddpreviewid)
//        var intdata = intent.getStringExtra("bitimgkey")
        if (param1 != null) {
            try {
                src = BitmapFactory.decodeStream(requireActivity().openFileInput("ImageName"))
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        binding.cardimgid.setImageBitmap(src)
        binding.saveBtn.setOnClickListener {

            val bmp = AppUtils.getBitmapFromView(binding.cardimgid)
            AppUtils.saveBitmap(bmp, "Cards", requireContext())
//            AppUtils.saveQuotesFrames(requireActivity(), binding.cardimgid, "", "CREATE CARD")
            AppUtils.showDownloadAlert1(requireActivity(), "Download", "Saved Successfully!!")
        }
        binding.shareBtn.setOnClickListener {
            AppUtils.OnClickShare(binding.cardimgid, requireContext())
        }


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateCardPreviewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateCardPreviewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Card Preview"
        super.onViewCreated(view, savedInstanceState)

//        val callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                findNavController().popBackStack()
//
//            }
//        }
//
//        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

}