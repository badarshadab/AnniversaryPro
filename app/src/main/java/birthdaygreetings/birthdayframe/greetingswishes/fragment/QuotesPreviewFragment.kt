package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.adapter.QuotesPreviewAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.FragmentQuotesPreviewBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils

class QuotesPreviewFragment : Fragment(), View.OnClickListener {

    private lateinit var b: FragmentQuotesPreviewBinding
    private var index: Int? = null
    private lateinit var name: String
    lateinit var listA: ArrayList<String>
    var swipeCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        println("Twice Call inside onCreate")
        name = arguments?.getString("name").toString()
        index = arguments?.getInt("pos", 0)
        listA = arguments?.getStringArrayList("ArrayList") as ArrayList<String>
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                findNavController().popBackStack()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentQuotesPreviewBinding.inflate(inflater, container, false)
//        println("Twice Call inside onCreateView")

        AdUtils.showNativeBanner(requireActivity(), b.adContainer.nativeAdContainer)
//        println("Twice Call getQuotes")
        listA.let { list ->
//            println("Twice Call inside observe")
            if (!list.isNullOrEmpty()) {
                b.copy.setOnClickListener(this)
                b.shareText.setOnClickListener(this)
            }
//            println("Twice Call 2")
            b.shimmerLay.startShimmer()


            if (listA.size > 0) {
                b.shimmerLay.stopShimmer()
                b.vp.visibility = View.VISIBLE
                b.shimmerLay.visibility = View.GONE
            }
            val adapter = listA?.let { activity?.let { it1 -> QuotesPreviewAdapter(it1, it) } }
            b.vp.adapter = adapter
//            println("Twice Call 3 " + index)
            b.vp.currentItem = index!!
        }

        AppUtils.getViewpager2SwipeCount(requireActivity(), b.vp)

        return b.root
    }

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {

                R.id.copy -> {
                    val adapter = b.vp.adapter as QuotesPreviewAdapter
                    copyText(adapter.getItem(b.vp.currentItem))
                }

                R.id.shareText -> {
                    val adapter = b.vp.adapter as QuotesPreviewAdapter
                    shareText(adapter.getItem(b.vp.currentItem))
                }

                else -> {

                }
            }
        }
    }

    private fun shareText(str: String) {
        AppUtils.shareString(requireContext(), str)
    }

    private fun copyText(str: String) {
        AppUtils.copyTextToClipBoard(requireContext(), str)
    }


}