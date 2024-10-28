package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import birthdaygreetings.birthdayframe.greetingswishes.adapter.DailyWishesContentPreviewAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.FragmentCelebrityCardPreviewBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import com.google.firebase.storage.StorageReference


class CelebrityCards_PreviewFragment : Fragment() {
    private lateinit var b: FragmentCelebrityCardPreviewBinding

    private var type: String = ""
    private var category: String = ""
    private var celeb_name: String = ""
    var index: Int? = 0

    //    private lateinit var mainViewModel: HomeViewModel
    var toolText = ""


    lateinit var activity: Activity

    companion object {
        lateinit var any: List<StorageReference>
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        if (wishesName != "null") {
//            toolText = "$wishesName $type"
//        } else {
        toolText = "Birthday Cards"
//        }

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = toolText
        super.onViewCreated(view, savedInstanceState)
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
        b = FragmentCelebrityCardPreviewBinding.inflate(inflater, container, false)
        type = arguments?.getString("type").toString()
        category = arguments?.getString("catName").toString()
        celeb_name = arguments?.getString("celeb_name").toString()
        index = arguments?.getInt("position", 0)
//        println("arguments?.getInt(pos) " + index)
//        setupViewModel()
//        if (!wishesName.equals("null")) {
//            mainViewModel.loadImagesStorage("$category/$wishesName/$type")
//        } else {
//            mainViewModel.loadImagesStorage("$category/$type")
//        }

        setupObservers()
        AdUtils.showNativeBanner(
            requireActivity(),
            b.nativeAdContainer
        )

        return b.root
    }

//    private fun setupViewModel() {
//        val myViewModelFactory = MyViewModelFactory(FirebaseHelper())
//        mainViewModel =
//            ViewModelProvider(this, myViewModelFactory)[HomeViewModel::class.java]
//    }

    private fun setupObservers() {
//        mainViewModel.repositoryResponseLiveData_ImageStore.observe(requireActivity()) { resource ->
//            setAdapter(resource)
//        }
        setAdapter(any)
    }

    private fun setAdapter(resource: List<StorageReference>) {
        var list = resource.asReversed()
        val adapter = DailyWishesContentPreviewAdapter(
            activity, resource, type
        )
        b.vp.adapter = adapter
        b.vp.setCurrentItem(index!!, true)
        AppUtils.getViewpager2SwipeCount(requireActivity(), b.vp)
    }

}