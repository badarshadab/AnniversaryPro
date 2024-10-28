package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.adapter.CreateCardsAdapter
import birthdaygreetings.birthdayframe.greetingswishes.databinding.GifActivityMainBinding
import birthdaygreetings.birthdayframe.greetingswishes.databinding.SelectTypeDialogBinding
import birthdaygreetings.birthdayframe.greetingswishes.model.Family
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils


class FamilyCategoryFragment : Fragment() {
    private lateinit var b: GifActivityMainBinding
    var name = ""
    var title = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = GifActivityMainBinding.inflate(inflater, container, false)

//        val intdata : Family = arguments?.getParcelableArray("familyList" )
//        val intdata  = arguments?.getParcelableArray("familyList" )
        name = arguments?.getString("catName").toString()
        title = arguments?.get("title").toString()
        AdUtils.showAdaptiveBanner(requireActivity(), b.nativeAd.nativeAdContainer)
        val parcelableArray: Array<Parcelable>? = arguments?.getParcelableArray("familyList")
        val familyArray: Array<Family>? = parcelableArray?.map { it as Family }?.toTypedArray()

// Now you can work with familyArray, which should be an array of Family objects


        println("intdata $familyArray")
        b.rv.layoutManager = GridLayoutManager(
            requireContext(), 2
        )

        val adapter = familyArray?.let {
            CreateCardsAdapter(
                requireActivity(),
                it,
                object : CreateCardsAdapter.RecyclerViewClickListener {
                    override fun onClick(view: View?, position: Int, catName: String?) {
                        var sub_cat_name = it.get(position).name
                        openAddPhotoDialog(sub_cat_name)
//                        val b = Bundle()
//                        b.putString("catName", "$name/$sub_cat_name")
//                        AppUtils.changeFragmentWithPosition(
//                            findNavController(), R.id.cat_type_frag, requireActivity(),b
//                        )
                    }
                })
        }
        b.rv.adapter = adapter
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

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun openAddPhotoDialog(sub_cat_name: String?) {

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val addPhotoDialogBinding = SelectTypeDialogBinding.inflate(layoutInflater)
        builder.setView(addPhotoDialogBinding.root)
        val dialog: AlertDialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        addPhotoDialogBinding.subCatName.text = sub_cat_name

        addPhotoDialogBinding.quotes.setOnClickListener {
            val b = Bundle()
            b.putString("catName", "$name/$sub_cat_name/Quotes")
            b.putString("title", sub_cat_name)
            AppUtils.changeFragmentWithPosition(
                findNavController(), R.id.quotesfrag, requireActivity(), b
            )
            dialog.dismiss()
        }
        addPhotoDialogBinding.images.setOnClickListener {
            val b = Bundle()
            b.putString("catName", "$name/$sub_cat_name/Cards")
            b.putString("title", sub_cat_name)
            AppUtils.changeFragmentWithPosition(
                findNavController(), R.id.gifsorryfrag, requireActivity(), b
            )

            dialog.dismiss()
        }

    }


}