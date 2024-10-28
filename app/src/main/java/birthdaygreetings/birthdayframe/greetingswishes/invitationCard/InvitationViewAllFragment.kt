package birthdaygreetings.birthdayframe.greetingswishes.invitationCard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.databinding.FragmentInvitationViewAllBinding
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.adapter.InvitationViewAllAdapter
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.model.Component
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.model.InvitationModelItem
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.viewModel.ComponentViewModel
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.viewModel.InvitationViewModel
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils


class InvitationViewAllFragment : Fragment() {
    private lateinit var binding: FragmentInvitationViewAllBinding
    private lateinit var invitationViewAllAdapter: InvitationViewAllAdapter
    var position: Int = 0
    private lateinit var viewModel: InvitationViewModel
    private var invitationImageList = ArrayList<String>()
    private var sampleUrlList = ArrayList<String>()
    private var invitationList = ArrayList<InvitationModelItem>()
    private var componentList = ArrayList<Component>()
    private var componentList1 = ArrayList<Component>()
    private var title: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = 0
        title = "Birthday Invitation"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInvitationViewAllBinding.inflate(layoutInflater, container, false)
        AdUtils.showAdaptiveBanner(requireActivity(), binding.adAdvertiser)
//        MainFragment.pressed_btn = 10
//        InvitationEditFragment.isLoaded = false
        loadAdapter()
        loadData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = title
        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadData() {
        viewModel = ViewModelProvider(requireActivity())[InvitationViewModel::class.java]
        viewModel.getPopularInvitations()
        viewModel.invitationList.observe(viewLifecycleOwner, Observer { invitationModel ->
            invitationImageList.clear()
            sampleUrlList.clear()
            invitationList.addAll(invitationModel)
            for (items in invitationModel[position].template) {
                invitationImageList.add(items.img_url)
                sampleUrlList.add(items.sample_url)
            }
            invitationViewAllAdapter.setCategories(sampleUrlList)
            binding.prg.visibility = View.GONE
            if (sampleUrlList.isNotEmpty()) {
//                AdUtils.showAdaptiveBanner(requireActivity(),binding.adAdvertiser)
            }
        })
        binding.rvInvitationViewAll.adapter = invitationViewAllAdapter
    }

    private fun loadAdapter() {
        val bundle = Bundle()
        invitationViewAllAdapter = InvitationViewAllAdapter() { imgPosition ->
            when (imgPosition) {
                imgPosition -> {
                    bundle.putString("bgUrl", invitationImageList[imgPosition])
                    bundle.putInt("catPosition", position)
                    bundle.putInt("imgPosition", imgPosition)

                    componentList1.clear()
                    for (items in invitationList[position].template[imgPosition].component) {
                        componentList1.add(
                            Component(
                                items.com_Ypos,
                                "",
                                1,
                                "",
                                items.font,
                                items.text_color,
                                items.text_size
                            )
                        )
                    }
                    val componentViewModel: ComponentViewModel by lazy {
                        ViewModelProvider(
                            requireActivity()
                        )[ComponentViewModel::class.java]
                    }
                    componentViewModel.setImage(componentList1)

                    if (position == 1) {
                        bundle.putString("title", "Wedding Invitation")
                        AppUtils.changeFragmentWithPosition(
                            findNavController(),
                            R.id.action_invitationviewall_frag_to_invitation_edit_frag,
                            requireActivity(),
                            bundle
                        )
                    } else if (position == 0) {
                        bundle.putString("title", title)
                        componentList.clear()
                        for (items in invitationList[position].template[imgPosition].component) {
                            componentList.add(
                                Component(
                                    items.com_Ypos,
                                    "",
                                    1,
                                    "",
                                    items.font,
                                    items.text_color,
                                    items.text_size
                                )
                            )
                        }
                        val componentViewModel: ComponentViewModel by lazy {
                            ViewModelProvider(
                                requireActivity()
                            )[ComponentViewModel::class.java]
                        }
                        componentViewModel.setImage(componentList)
                        AppUtils.changeFragmentWithPosition(
                            findNavController(),
                            R.id.action_invitationviewall_frag_to_birthday_invitation_frag,
                            requireActivity(),
                            bundle
                        )
                    } else {
                        bundle.putString("title", "Anniversary Invitation")
                        AppUtils.changeFragmentWithPosition(
                            findNavController(),
                            R.id.action_invitationviewall_frag_to_invitation_edit_frag2,
                            requireActivity(),
                            bundle
                        )
                    }
                }
            }
        }
    }
}