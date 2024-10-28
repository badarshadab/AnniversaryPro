package birthdaygreetings.birthdayframe.greetingswishes.invitationCard

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import birthdaygreetings.birthdayframe.greetingswishes.databinding.FragmentCardPreviewBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.ShareUtils
import birthdaygreetings.birthdayframe.greetingswishes.viewmodel.IVCardShareViewModel


class CardPreviewFragment : Fragment() {

    private lateinit var binding: FragmentCardPreviewBinding
    private var imgList = ArrayList<String>()
    private var shareImage: Bitmap? = null
    private var cardType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cardType = arguments?.getString("cardType").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardPreviewBinding.inflate(layoutInflater, container, false)
        imgList.addAll(AppUtils.getCardCollection1(requireContext()))
        println("savedList$imgList")

        val ivCardShareViewModel: IVCardShareViewModel by lazy { ViewModelProvider(requireActivity())[IVCardShareViewModel::class.java] }
        ivCardShareViewModel.shareImages.observe(viewLifecycleOwner, Observer {
            println("shareImages.observe $it")
            if (cardType == "BUSINESS") {
                binding.card1.visibility = View.INVISIBLE
                //Glide.with(requireContext()).load(it.card).into(binding.ivSavedBusinessImg)
                binding.ivSavedBusinessImg.setImageBitmap(it.card)
                AdUtils.showAdaptiveBanner(requireActivity(), binding.llAd)
            } else {
                //Glide.with(requireContext()).load(it.card).into(binding.ivSavedImg)
                binding.ivSavedImg.setImageBitmap(it.card)
                AdUtils.showAdaptiveBanner(requireActivity(), binding.llAd)
            }
            shareImage = it.card
        })
        buttonClick()
        return binding.root
    }

    private fun buttonClick() {
        binding.tvShare.setOnClickListener {
            shareImage?.let { it1 -> AppUtils.shareBitmap(requireContext(), it1) }
        }

        binding.tvWhatsapp.setOnClickListener {
            shareImage?.let { it1 -> AppUtils.shareBitmapToWhatsapp(requireContext(), it1) }
        }

        binding.tvDownload.setOnClickListener {
            shareImage?.let {
                ShareUtils.saveBitmap(it , "Invitation",requireContext())
            }
//            shareImage?.let { it1 -> AppUtils.saveBitmap(it1, "", requireContext()) }
//            AppUtils.showDownloadAlert1(findNavController(),R.id.action_cardPreviewFragment_to_myDesignFragment,requireActivity(),shareImage,requireContext())
            //findNavController().navigate(R.id.action_cardPreviewFragment_to_myDesignFragment)
        }
    }
}