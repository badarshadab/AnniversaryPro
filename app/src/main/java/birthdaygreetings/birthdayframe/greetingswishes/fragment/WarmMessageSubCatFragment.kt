package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.adapter.QAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.ActivityQuotesBinding
import birthdaygreetings.birthdayframe.greetingswishes.model.Warmmessage
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils


class WarmMessageSubCatFragment : Fragment() {
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
        val warmArray: Array<Warmmessage>? =
            parcelableArray?.map { it as Warmmessage }?.toTypedArray()

        AdUtils.showAdaptiveBanner(requireActivity(), b.nativeAd.nativeAdContainer)
//        val intdata = arguments?.getStringArrayList("ageList")
        val main_CatName = arguments?.getString("catName")
        val caller = arguments?.getString("caller")
        title = arguments?.getString("title").toString()
        println("intdata $caller")

        b.rv.layoutManager = GridLayoutManager(
            requireContext(), 2
        )

        if (warmArray != null) {
            b.shimmerLayQuotes.stopShimmer()
            b.rv.visibility = View.VISIBLE
            b.shimmerLayQuotes.visibility = View.GONE
        }

        val adapter = warmArray?.let {
            QAdapter(it, object : QAdapter.RecyclerViewClickListener {
                override fun onClick(view: View?, position: Int, catName: String?) {
                    val name = warmArray[position]
                    val b = Bundle()
//                    b.putString("main_CatName" , main_CatName)
//                    Toast.makeText(requireContext(), it[position].name, Toast.LENGTH_SHORT).show()
                    if (caller.equals("ageSpecific")) {
                        b.putString("catName", "$main_CatName/$name")
//                        b.putString("title", it[position].name)
                        AppUtils.changeFragmentWithPosition(
                            findNavController(), R.id.gifsorryfrag, requireActivity(), b
                        )
                    } else {
                        val nName = warmArray[position].name
                        b.putString("catName", "$main_CatName/$nName")
                        b.putString("title", it[position].name)
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