package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import birthdaygreetings.birthdayframe.greetingswishes.adapter.SavedViewpagerAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.SavedmainfragmentBinding

class SavedMainFragment : Fragment() {

    lateinit var _binding: SavedmainfragmentBinding
    var list = ArrayList<String>()

    lateinit var tablayout: TabLayout
    lateinit var viewpager: ViewPager2
    lateinit var typeArray: Array<String>
    private var clickedType: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        _binding = SavedMainFragment.inflate(inflater, container, false)
        _binding = SavedmainfragmentBinding.inflate(inflater, container, false)

        viewpager = _binding.vp
        tablayout = _binding.tl
        clickedType = arguments?.getString("from").toString()

        if (clickedType.equals("daily")) {
            typeArray = arrayOf(
                "Gifs", "Cards", "Quotes", "Invitation"
            )
        } else {
            typeArray = arrayOf(
                "Gifs", "Frames", "Cards", "Invitation"
            )
        }

//        println("in oncreate of Holday_list")
//        AdUtils.showNativeBanner(
//            requireActivity(),
//            _binding.nativeAdContainer.nativeAdContainer
//        )
        viewpager.adapter = SavedViewpagerAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            requireActivity(),
            typeArray,
            clickedType
        )

        TabLayoutMediator(tablayout, viewpager) { tab, position ->
            tab.text = typeArray[position]
        }.attach()

        return _binding.root
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