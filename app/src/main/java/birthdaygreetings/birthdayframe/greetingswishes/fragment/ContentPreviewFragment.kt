package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import birthdaygreetings.birthdayframe.greetingswishes.adapter.DailyWishesContentPreviewAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.FragmentContentPreviewBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import birthdaygreetings.birthdayframe.greetingswishes.viewmodel.FamilyCardsViewModel
import com.google.firebase.storage.StorageReference


class ContentPreviewFragment : Fragment(), View.OnClickListener {
    private lateinit var b: FragmentContentPreviewBinding

    private var category: String = ""
    private var wishesName: String = ""
    var index: Int? = 0
    var toolText = ""
    var title = ""
    lateinit var prevList: List<StorageReference>
    lateinit var activity: Activity

    companion object {
        lateinit var any: Any
    }

//    lateinit var activity: Activity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (wishesName != "null") {
            toolText = "$wishesName"
        } else {
            toolText = "$category"
        }
//        (activity as AppCompatActivity?)!!.supportActionBar!!.title = toolText
        super.onViewCreated(view, savedInstanceState)

//        val callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                findNavController().popBackStack()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as Activity
//        https://stackoverflow.com/questions/28672883/java-lang-illegalstateexception-fragment-not-attached-to-activity
//        Fragment ContentPreviewFragment{fb22d83} (743b8906-1fa7-4828-8024-cc60ff8aac63) not attached to an activity.
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentContentPreviewBinding.inflate(inflater, container, false)
        category = arguments?.getString("catName").toString()
        index = arguments?.getInt("pos", 0)
        title = arguments?.getString("title").toString()
        prevList = any as List<StorageReference>
        AdUtils.showAdaptiveBanner(requireActivity(), b.adContainer.nativeAdContainer)
        setupObservers(category)

        return b.root
    }

    private fun setupObservers(categoryName: String) {

        val gifsViewModel: FamilyCardsViewModel by lazy { ViewModelProvider(this)[FamilyCardsViewModel::class.java] }
        gifsViewModel.getCategoryWiseCards(categoryName)
            .observe(viewLifecycleOwner, Observer { list ->
                if (list.isNotEmpty()) {
                    b.shimmerLay.stopShimmer()
                    b.shimmerLay.visibility = View.GONE
                }
                setAdapter(prevList)
            })
    }

    private fun setAdapter(resource: List<StorageReference>) {
//        var list = resource.asReversed()
        val adapter = DailyWishesContentPreviewAdapter(
            requireActivity(), resource, title
        )
        b.vp.adapter = adapter
        b.vp.setCurrentItem(index!!, true)
        AppUtils.getViewpager2SwipeCount(requireActivity(), b.vp)
        println("setCurrentItem(index!! $index")
    }

    override fun onClick(v: View?) {
//        when(v){
//            b.share -> {
//
//            }
//            b.save ->{
//
//            }
//        }
    }
}