package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import birthdaygreetings.birthdayframe.greetingswishes.adapter.GifImageAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.ActivityGifBinding
import com.google.firebase.storage.StorageReference


class InvitationFragment : Fragment() {
    private lateinit var b: ActivityGifBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = ActivityGifBinding.inflate(inflater, container, false)

        return b.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Invitation"
        super.onViewCreated(view, savedInstanceState)

//        val callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                findNavController().popBackStack()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }


    private fun setUpObserver(categoryName: String) {


    }

    private fun setImageAdapter(it: List<StorageReference>) {

        b.rv.adapter = GifImageAdapter(requireActivity(),
            it,
            object : GifImageAdapter.RecyclerViewClickListener {
                override fun onClick(view: View?, position: Int) {
                    ContentPreviewFragment.any = it
                    val b = Bundle()
                    b.putInt("pos", position)
//                        b.putString("catName" , name)
//                        b.putString("title" , title)
                }
            })

    }


}