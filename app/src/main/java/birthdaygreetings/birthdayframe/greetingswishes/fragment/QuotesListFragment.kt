package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import birthdaygreetings.birthdayframe.greetingswishes.adapter.QuotesListAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.ActivityQuotesBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.viewmodel.QuoteViewModel


class QuotesListFragment : Fragment() {
    private lateinit var b: ActivityQuotesBinding
    private lateinit var quotesViewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = ActivityQuotesBinding.inflate(inflater, container, false)
        setupViewModel()


        AdUtils.showAdaptiveBanner(requireActivity(), b.nativeAd.nativeAdContainer)
        val catName = arguments?.getString("")
        b.rv.layoutManager = GridLayoutManager(requireContext(), 1)
        if (catName != null) {
            setupObservers(catName)
        }
        return b.root
    }

    override fun onResume() {
        super.onResume()
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

    private fun setupViewModel() {
        quotesViewModel =
            ViewModelProvider(this)[QuoteViewModel::class.java]
    }

    private fun setupObservers(categoryName: String) {


        quotesViewModel.getQuotes(categoryName)
        quotesViewModel.quotes.observe(requireActivity()) { list ->
            println("list $list")
            setQuotesAdapter(list as ArrayList<String>)
        }
    }

    private fun setQuotesAdapter(list: ArrayList<String>) {

        b.rv.adapter = QuotesListAdapter(
            requireActivity(),
            list,
            object : QuotesListAdapter.RecyclerViewClickListener {
                override fun onClick(view: View?, position: Int) {
//                    val b = Bundle()
//                    b.putString("name", "DailyWishes/" + name)
//                    b.putInt("pos", position)
//                    b.putStringArrayList("ArrayList", list)
//                    AppUtils.changeFragmentWithPosition(
//                        findNavController(),
//                        R.id.action_nav_content_list_to_nav_quotesPreview,
//                        requireActivity(),
//                        b
//                    )
                }
            })
    }

}