package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.adapter.CardAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.FragmentCelebrityPreviewBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.OnItemClickListener_Gif
import birthdaygreetings.birthdayframe.greetingswishes.viewmodel.CardViewModel
import birthdaygreetings.birthdayframe.greetingswishes.viewmodel.HomeViewModel
import com.google.firebase.storage.StorageReference


class CelebrityPreviewFragment : Fragment() {
    private lateinit var b: FragmentCelebrityPreviewBinding

    private var type: String = ""
    private var category: String = ""
    private var wishesName: String = ""
    private var img_addrs: String = ""
    private var celeb_birthday: String = ""
    private var info: String = ""
    private var celeb_name: String = ""
    var index: Int? = 0
    var monthName = "January"


    private var list: List<StorageReference>? = null
    private lateinit var mainViewModel: HomeViewModel

    //    private lateinit var mainViewModel: HomeViewModel
    var toolText = ""


    lateinit var activity: Activity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        toolText = "$celeb_name"


        (activity as AppCompatActivity?)!!.supportActionBar!!.title = toolText
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentCelebrityPreviewBinding.inflate(inflater, container, false)


        AppUtils.setImageWithRoundCorner(img_addrs, b.celebIcon, 40, 400)
        b.tvCelebName.text = celeb_name
        b.celebBirthday.text = celeb_birthday
        b.description.text = info
        b.about.text = "About $celeb_name"
        setupObservers("Events/${monthName}/${celeb_name}")
        AdUtils.showNativeBanner(
            requireActivity(),
            b.adContainer.nativeAdContainer
        )
        val dateString = celeb_birthday  // Your date string
        val parts = dateString.split("-")  // Split the string by the hyphen

        val month = parts[1]  // Extract the second part, which is the month
        monthName = getMonthName(month.toInt())
//        println("Month name is : $monthName")


        b.cardRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        return b.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.getString("type").toString()
        category = arguments?.getString("catName").toString()
        wishesName = arguments?.getString("wishesName").toString()
        img_addrs = arguments?.getString("img_addrs").toString()
        celeb_name = arguments?.getString("celeb_name").toString()
        celeb_birthday = arguments?.getString("date").toString()
        info = arguments?.getString("info").toString()
        index = arguments?.getInt("position", 0)
    }

    override fun onResume() {
        super.onResume()
        setupObservers("Events/${monthName}/${celeb_name}")
    }

    private fun setupObservers(categoryName: String) {

        val framesViewModel: CardViewModel by lazy { ViewModelProvider(this)[CardViewModel::class.java] }
        framesViewModel.getCategoryWiseCards(categoryName)
            .observe(viewLifecycleOwner, Observer { resource ->
                this.list = resource.asReversed()
                setAdapter(list!!)
            })

    }

    private fun setAdapter(resource: List<StorageReference>) {
        var adapter = CardAdapter(resource, activity, object :
            OnItemClickListener_Gif {
            override fun onClick(position: Int) {
                val b = Bundle()
                b.putString("celeb_name", celeb_name)
                b.putInt("position", position)
                CelebrityCards_PreviewFragment.any = resource
                AppUtils.changeFragment(requireActivity(), R.id.celebrities_card_prev_frag, b)
            }
        })
        b.cardRv.adapter = adapter
    }

    fun getMonthName(monthNumber: Int): String {
        return when (monthNumber) {
            1 -> "January"
            2 -> "February"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            12 -> "December"
            else -> "Invalid month"  // Handle invalid month numbers
        }
    }


}