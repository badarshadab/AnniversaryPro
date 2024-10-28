package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.storage.StorageReference
import birthdaygreetings.birthdayframe.greetingswishes.databinding.ActivityNamePeomMainBinding
import birthdaygreetings.birthdayframe.greetingswishes.viewmodel.FamilyCardsViewModel


class NamePoemMainFragment : Fragment() {
    private lateinit var b: ActivityNamePeomMainBinding


    val bdaytempimg = ArrayList<StorageReference>()
    val bdaythumb = ArrayList<StorageReference>()

    var name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = ActivityNamePeomMainBinding.inflate(inflater, container, false)
//        b.rv.layoutManager = GridLayoutManager(requireContext(), 3)
        name = arguments?.getString("catName").toString()
        setUpObserver(name)
        return b.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpObserver(categoryName: String) {
        val gifsViewModel: FamilyCardsViewModel by lazy { ViewModelProvider(this)[FamilyCardsViewModel::class.java] }
        gifsViewModel.getCategoryWiseCards(categoryName)
            .observe(viewLifecycleOwner, Observer { list ->
                setImageAdapter(list)
            })
    }


    private fun setImageAdapter(it: List<StorageReference>) {

//        b.rv.adapter = GifImageAdapter(requireActivity(),
//            it,
//            object : GifImageAdapter.RecyclerViewClickListener {
//                override fun onClick(view: View?, position: Int) {
//                    val b = Bundle()
////                    NamePoemResultFragment.backgroundimgName = it[position]
////                    b.putString("name", "Shadab")
////                    b.putString("color", "black")
////                    b.putString("align", "left")
////                    b.putString("imgname", "template1")
//
//                    val i = Intent(activity, NamePoemResultactivity1::class.java)
////                    NamePoemResultactivity1.backgroundimgName  = it[position]
//                    i.putExtra("bgimg", R.drawable.addtextdonebtn)
//                    i.putExtra("name", "Shadab")
//                    i.putExtra("color", "black")
//                    i.putExtra("align", "left")
//                    i.putExtra("imgname", "template1")
//                    startActivity(i)
//                    (activity as Activity?)!!.overridePendingTransition(0, 0)
//
////                    AppUtils.changeFragmentWithPosition(
////                        findNavController(),
////                        R.id.action_name_poem_frag_to_name_poem_result_frag,
////                        requireActivity(),
////                        b
////                    )
//                }
//
//            })

    }

//    private fun genratePoem() {
//        val str = ""
//        if (b.editname.text.isEmpty()) {
//            Toast.makeText(requireContext(), "Please enter your name.", Toast.LENGTH_LONG)
//                .show()
//            return
//        }
//        val booleanValue: Boolean = bgselected
//        val str2 = " "
//        val str3 = "name"
//        if (!booleanValue) {
//            intent.putExtra("bgimg", bdaytempimg[1])
//            intent.putExtra(str3, name.getText().toString().replace(str2.toRegex(), str))
//            intent.putExtra("color", "black")
//            intent.putExtra("align", "left")
//            intent.putExtra("imgname", "template1")
//            startActivity(intent)
//            return
//        }
//        intent.putExtra(str3, name.getText().toString().replace(str2.toRegex(), str))
//        startActivity(intent)
//    }


}