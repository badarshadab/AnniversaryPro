package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.adapter.QuotesListAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.ActivityQuotesBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import birthdaygreetings.birthdayframe.greetingswishes.viewmodel.QuoteViewModel


class QuotesFragment : Fragment() {
    private lateinit var b: ActivityQuotesBinding
    private lateinit var quotesViewModel: QuoteViewModel
    var main_CatName: String = ""
    var toolbarTitle = ""
    var title = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = ActivityQuotesBinding.inflate(inflater, container, false)
        setupViewModel()


        b.rv.layoutManager = GridLayoutManager(requireContext(), 1)

        AdUtils.showAdaptiveBanner(requireActivity(), b.nativeAd.nativeAdContainer)
        main_CatName = arguments?.getString("catName").toString()
        title = arguments?.getString("title").toString()

        if (main_CatName != null) {
            setupObservers(main_CatName)
        }
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

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        activity = context as Activity
//    }

    private fun setupViewModel() {
        quotesViewModel =
            ViewModelProvider(this)[QuoteViewModel::class.java]
    }

    private fun setupObservers(categoryName: String) {

        quotesViewModel.getQuotes(categoryName)
        quotesViewModel.quotes.observe(viewLifecycleOwner) { list ->
            println("list $list")
            setQuotesAdapter(list as ArrayList<String>)
        }
    }

    override fun onResume() {

        b.shimmerLayQuotes.visibility = View.VISIBLE
        b.rv.layoutManager = GridLayoutManager(requireContext(), 1)
        super.onResume()
    }

    private fun setQuotesAdapter(list: ArrayList<String>) {
        if (list != null) {

            b.shimmerLayQuotes.stopShimmer()
            b.rv.visibility = View.VISIBLE
            b.shimmerLayQuotes.visibility = View.GONE
        }
        b.rv.adapter = QuotesListAdapter(
            requireActivity(),
            list,
            object : QuotesListAdapter.RecyclerViewClickListener {
                override fun onClick(view: View?, position: Int) {
                    val b = Bundle()
                    b.putString("catName", main_CatName)
                    b.putInt("pos", position)
                    b.putStringArrayList("ArrayList", list)
                    AppUtils.changeFragmentWithPosition(
                        findNavController(),
                        R.id.action_quotesfrag_to_quotes_previewfrag,
                        requireActivity(),
                        b
                    )
                }
            })
    }

}