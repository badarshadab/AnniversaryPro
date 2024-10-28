package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import birthdaygreetings.birthdayframe.greetingswishes.adapter.DownloadedAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.FragmentSavedBinding
import birthdaygreetings.birthdayframe.greetingswishes.model.FavoriteDataNew
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.ShareUtils


class SavedFragment(val pos: Int, val clickedType: String) : Fragment() {
    private lateinit var b: FragmentSavedBinding
    var list: ArrayList<String>? = null
    lateinit var favList: ArrayList<FavoriteDataNew>
    lateinit var downloadedAdapter: DownloadedAdapter
    private var type: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentSavedBinding.inflate(inflater, container, false)
//        setupViewModel()
        favList = ArrayList()


        if (clickedType.equals("daily")) {
            type = when (pos) {
                0 -> "Gif"
                1 -> "Card"
                2 -> "Quote"
                3 -> "Invitation"
                else -> { // Note the block
                    ""
                }
            }
        } else {
            type = when (pos) {
                0 -> "Gifs"
                1 -> "Frames"
                2 -> "Cards"
                3 -> "Invitation"
                else -> { // Note the block
                    ""
                }
            }
        }


        list = ShareUtils.getCollection(requireActivity(), "/" + type)

        if (list!!.size > 0) {
            b.nosaved.visibility = View.GONE
            if(list!!.size > 3){
                AdUtils.showAdaptiveBanner(requireActivity(), b.adContainer.nativeAdContainer)
            }
        } else {
            b.nosaved.visibility = View.VISIBLE
        }
        b.rv.setLayoutManager(
            LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        downloadedAdapter = DownloadedAdapter(list!!, requireActivity())
        list?.reverse()
        b.rv.adapter = downloadedAdapter

        return b.root
    }

    override fun onResume() {
        super.onResume()
        if (list!!.size <= 2) {

        } else {
//            AdUtils.showNativeBanner(
//                requireActivity(),
//                b.adContainer.nativeAdContainer
//            )
        }
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

}