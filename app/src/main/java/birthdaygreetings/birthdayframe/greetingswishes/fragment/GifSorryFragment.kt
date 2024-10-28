package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.adapter.GifImageAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.ActivityGifBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import birthdaygreetings.birthdayframe.greetingswishes.viewmodel.FamilyCardsViewModel
import com.google.firebase.storage.StorageReference


class GifSorryFragment : Fragment() {
    private lateinit var b: ActivityGifBinding
    var name = ""
    var title = ""
    var shuffledList: List<StorageReference> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = ActivityGifBinding.inflate(inflater, container, false)
//        val caller = arguments?.getInt("caller")


        name = arguments?.getString("catName").toString()
        title = arguments?.getString("title").toString()

        b.rv.layoutManager = GridLayoutManager(requireContext(), 3)
        if (name != null) {
            setUpObserver(name)
        }

        return b.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = title
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpObserver(categoryName: String) {
        val gifsViewModel: FamilyCardsViewModel by lazy { ViewModelProvider(this)[FamilyCardsViewModel::class.java] }
        gifsViewModel.getCategoryWiseCards(categoryName)
            .observe(viewLifecycleOwner, Observer { list ->
                if (list.size > 6) {
                    AdUtils.showAdaptiveBanner(requireActivity(), b.nativeAd.nativeAdContainer)
                }
                shuffledList = list.shuffled()
                setImageAdapter(shuffledList)
            })
    }

    private fun setImageAdapter(it: List<StorageReference>) {
        b.rv.adapter = GifImageAdapter(requireActivity(),
            it,
            object : GifImageAdapter.RecyclerViewClickListener {
                override fun onClick(view: View?, position: Int) {

                    if (name.equals("Name On Birthday Cake")) {
                        FrameEditFragment.any = it[position]
                        AppUtils.changeFragmentWithPosition(
                            findNavController(),
                            R.id.frame_edit,
                            requireActivity()
                        )
                    } else {
                        ContentPreviewFragment.any = it
                        val b = Bundle()
                        b.putInt("pos", position)
                        b.putString("catName", name)
                        b.putString("title", title)
                        AppUtils.changeFragmentWithPosition(
                            findNavController(),
                            R.id.action_gifsorryfrag_to_content_preview_frag,
                            requireActivity(),
                            b
                        )
                    }
                }
            })
    }


}