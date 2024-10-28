package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.adapter.AgeQAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.AgeSpecificLayoutBinding
import birthdaygreetings.birthdayframe.greetingswishes.model.Agespecific
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils


class AgeSpecificFragment : Fragment() {
    private lateinit var b: AgeSpecificLayoutBinding
    var title = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

//    override fun onResume() {
//
//        b.shimmerLayQuotes.visibility = View.VISIBLE
//        super.onResume()
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = AgeSpecificLayoutBinding.inflate(inflater, container, false)

        val parcelableArray: Array<Parcelable>? = arguments?.getParcelableArray("ageList")
        val warmArray: Array<Agespecific>? =
            parcelableArray?.map { it as Agespecific }?.toTypedArray()

        val main_CatName = arguments?.getString("catName")
        title = arguments?.get("title").toString()
        AdUtils.showAdaptiveBanner(requireActivity(), b.nativeAd.nativeAdContainer)
        b.rv.layoutManager = GridLayoutManager(
            requireContext(), 2
        )


        val adapter = warmArray?.let {
            AgeQAdapter(it, object : AgeQAdapter.RecyclerViewClickListener {
                override fun onClick(view: View?, position: Int, catName: String?) {
                    val b = Bundle()
                    val nName = warmArray[position].name
                    b.putString("catName", "$main_CatName/$nName")
                    b.putString("title", nName)
                    AppUtils.changeFragmentWithPosition(
                        findNavController(), R.id.gifsorryfrag, requireActivity(), b
                    )
                }

            })
        }

        b.rv.adapter = adapter
        return b.root
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


}