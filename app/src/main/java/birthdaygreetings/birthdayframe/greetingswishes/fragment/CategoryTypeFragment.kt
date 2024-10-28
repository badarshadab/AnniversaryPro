package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.databinding.CategoryTypeLayBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils


class CategoryTypeFragment : Fragment(), View.OnClickListener {
    private lateinit var b: CategoryTypeLayBinding
    private var name: String = ""
    var title = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = CategoryTypeLayBinding.inflate(inflater, container, false)
        name = arguments?.getString("catName").toString()
        title = arguments?.get("title").toString()
        AdUtils.showAdaptiveBanner(requireActivity(), b.nativeAd.nativeAdContainer)
        b.message.setOnClickListener(this)
        b.images.setOnClickListener(this)
        return b.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = title
        super.onViewCreated(view, savedInstanceState)

//        val callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                findNavController().popBackStack()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onClick(v: View?) {
        when (v) {
            b.message -> {
                val bun = Bundle()
                bun.putString("catName", "$name/Quotes")
                bun.putString("title", "$title Messages")
                AppUtils.changeFragmentWithPosition(
                    findNavController(), R.id.quotesfrag, requireActivity(), bun
                )
            }

            b.images -> {
                val bun = Bundle()
                bun.putString("catName", "$name/Cards")
                bun.putString("title", "$title Images")
                AppUtils.changeFragmentWithPosition(
                    findNavController(), R.id.gifsorryfrag, requireActivity(), bun
                )
            }
        }
    }


}