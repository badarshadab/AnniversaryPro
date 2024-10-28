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
import birthdaygreetings.birthdayframe.greetingswishes.adapter.ReligionQAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.ActivityQuotesBinding
import birthdaygreetings.birthdayframe.greetingswishes.model.Religiou
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils


class ReligiousSubCatFragment : Fragment() {
    private lateinit var b: ActivityQuotesBinding
    var title = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = ActivityQuotesBinding.inflate(inflater, container, false)

        val parcelableArray: Array<Parcelable>? = arguments?.getParcelableArray("ageList")
        val warmArray: Array<Religiou>? =
            parcelableArray?.map { it as Religiou }?.toTypedArray()

        b.shimmerLayQuotes.visibility = View.GONE
        b.rv.visibility = View.VISIBLE
        AdUtils.showAdaptiveBanner(requireActivity(), b.nativeAd.nativeAdContainer)
//        val intdata = arguments?.getStringArrayList("ageList")
        val main_CatName = arguments?.getString("catName")
        title = arguments?.get("title").toString()
//        val caller = arguments?.getString("caller")
//        println("intdata $caller")


        b.rv.layoutManager = GridLayoutManager(
            requireContext(), 2
        )

        val adapter = warmArray?.let {
            ReligionQAdapter(it, object : ReligionQAdapter.RecyclerViewClickListener {
                override fun onClick(view: View?, position: Int, catName: String?) {
                    val name = warmArray[position]
                    val b = Bundle()
                    val nName = warmArray[position].name
                    b.putString("catName", "$main_CatName/$nName")
                    b.putString("title", "$nName")
                    if (nName?.contains("Messages", ignoreCase = true)!!) {
                        AppUtils.changeFragmentWithPosition(
                            findNavController(),
                            R.id.quotesfrag, requireActivity(),
                            b
                        )
                    } else if (nName.contains("Images", ignoreCase = true)) {
                        AppUtils.changeFragmentWithPosition(
                            findNavController(), R.id.gifsorryfrag, requireActivity(), b
                        )
                    }


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