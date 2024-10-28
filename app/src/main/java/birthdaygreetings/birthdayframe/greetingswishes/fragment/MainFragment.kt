package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.activity.NamePoemMainActivity
import birthdaygreetings.birthdayframe.greetingswishes.databinding.MainFragmentLayoutNewBinding
import birthdaygreetings.birthdayframe.greetingswishes.model.RootNew
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.BIRTHDAYGIF_SCREEN
import birthdaygreetings.birthdayframe.greetingswishes.viewmodel.BirthdayProViewModel


class MainFragment : Fragment(), View.OnClickListener {
    private lateinit var b: MainFragmentLayoutNewBinding
    var rootData: RootNew? = null
    lateinit var activity: Activity
    private val mainViewModel: BirthdayProViewModel by lazy { ViewModelProvider(requireActivity())[BirthdayProViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as Activity
//        https://stackoverflow.com/questions/28672883/java-lang-illegalstateexception-fragment-not-attached-to-activity
//        Fragment ContentPreviewFragment{fb22d83} (743b8906-1fa7-4828-8024-cc60ff8aac63) not attached to an activity.
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        b = MainFragmentLayoutNewBinding.inflate(inflater, container, false)

//        b.videomaker.setOnClickListener(this)
        b.family.setOnClickListener(this)
        b.ageSpecif.setOnClickListener(this)
        b.friends.setOnClickListener(this)
        b.top100Msg.setOnClickListener(this)
        b.belated.setOnClickListener(this)
        b.birthdayGif.setOnClickListener(this)
        b.birthdayNamePoem.setOnClickListener(this)
        b.funny.setOnClickListener(this)
        b.happyBirthday.setOnClickListener(this)
        b.inspirational.setOnClickListener(this)
        b.love.setOnClickListener(this)
        b.nameOnBirthdayCake.setOnClickListener(this)
        b.religion.setOnClickListener(this)
        b.warmMessages.setOnClickListener(this)
        b.createCardsCv.setOnClickListener(this)
        b.birthdayInvitation.setOnClickListener(this)
//        b.celebrities.setOnClickListener(this)
//        b.funnyCardview.setBackgroundResource(R.drawable.curved_bg_gradient)
//        b.mainCardview.setBackgroundResource(R.drawable.curved_bg_gradient)
        mainViewModel.getComModel()

        AdUtils.showAdaptiveBanner(requireActivity(), b.nativeAd.nativeAdContainer)
        mainViewModel.repositoryResponseLiveData.observe(viewLifecycleOwner) { newData ->
            rootData = newData
        }
        return b.root
    }


    override fun onClick(v: View?) {
        when (v) {

//            b.videomaker -> {
//                val hjhds = rootData?.events
//                val intent = Intent(activity, MainActivityVideoMaker::class.java)
//                startActivity(intent)
                //                val b = Bundle()
//                AppUtils.changeFragmentWithPositionFromHomeFrag(
//                    findNavController(), R.id.action_nav_main_to_video_maker_options_frag, requireActivity(), b
//                )
//            }

            b.top100Msg -> {
                val top100MsgList = rootData?.top100msg
                val b = Bundle()
                b.putString("catName", "top100msg")
                b.putString("title", "Top 100 Messages")
//                b.putParcelable("top100MsgList", top100MsgList)
                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(), R.id.action_nav_main_to_quotesfrag, requireActivity(), b
                )
            }

            b.birthdayGif -> {
                val birthdayGifList = rootData?.birthdaygif
                val ccc = rootData?.createcards
                val b = Bundle()
                b.putInt("caller", BIRTHDAYGIF_SCREEN)
                b.putString("catName", birthdayGifList?.name)
                b.putParcelable("friendsList", birthdayGifList)
                b.putString("title", "Birthday Gif")
                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(), R.id.gifsorryfrag, requireActivity(), b
                )
            }

            b.warmMessages -> {
                var familyList = rootData?.warmmessage
                val b = Bundle()
                b.putString("catName", "warmmessage")
                b.putString("caller", "warmMessages")
                b.putString("title", "Warm Messages")
                b.putParcelableArray("ageList", familyList?.toTypedArray())
                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(), R.id.warm_messagessubcat, requireActivity(), b
                )
            }

            b.happyBirthday -> {
                val birthdayGifList = rootData?.happybirthday
                val b = Bundle()
                b.putString("catName", birthdayGifList?.name)
                b.putParcelable("friendsList", birthdayGifList)
                b.putString("title", "Happy Birthday")
                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(), R.id.gifsorryfrag, requireActivity(), b
                )
            }

            b.family -> {
                var familyList = rootData?.family
                val b = Bundle()
                b.putString("catName", "Family")
                b.putString("title", "Family")
                b.putParcelableArray("familyList", familyList?.toTypedArray())
                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(),
                    R.id.familycatfrag,
                    requireActivity(),
                    b
                )
            }

            b.inspirational -> {
                val inspirationalList = rootData?.inspiration
                val b = Bundle()
                b.putString("catName", inspirationalList?.name)
                b.putString("title", "Inspirational")
                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(), R.id.cat_type_frag, requireActivity(), b
                )
            }

            b.funny -> {
                val funnyList = rootData?.funny
                val b = Bundle()
                b.putString("catName", funnyList?.name)
                b.putString("title", "Funny")
                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(), R.id.cat_type_frag, requireActivity(), b
                )
            }

            b.friends -> {
                val b = Bundle()
                var friendsList = rootData?.friends
//                b.putInt("caller" , )
//                b.putParcelable("friendsList", friendsList)
                b.putString("catName", friendsList?.name)
                b.putString("title", "Friends")

                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(), R.id.gifsorryfrag, requireActivity(), b
                )
            }

            b.love -> {
                val b = Bundle()
                var loveList = rootData?.love
//                b.putParcelable("friendsList", friendsList)
                b.putString("catName", loveList?.name)
                b.putString("title", "Love")
                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(), R.id.gifsorryfrag, requireActivity(), b
                )
            }

            b.ageSpecif -> {
                var familyList = rootData?.agespecific
                val b = Bundle()
                b.putParcelableArray("ageList", familyList?.toTypedArray())
                b.putString("caller", "ageSpecific")
                b.putString("catName", "agespecific")
                b.putString("title", "Age Specific")
//                b.putParcelableArray("ageList" , familyList?.toTypedArray())
                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(), R.id.age_specific, requireActivity(), b
                )
            }

            b.religion -> {
                var religiousList = rootData?.religious
                val b = Bundle()
                b.putString("catName", "Religious")
                b.putString("title", "Religious")
                b.putParcelableArray("ageList", religiousList?.toTypedArray())
                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(), R.id.religion_subcat, requireActivity(), b
                )

            }

            b.birthdayNamePoem -> {
                AdUtils.showFullAd(requireActivity(), object : AdUtils.AdListener {
                    override fun onComplete() {
                        val i = Intent(activity, NamePoemMainActivity::class.java)
                        startActivity(i)
                        (activity as Activity?)!!.overridePendingTransition(0, 0)
                    }

                })

//                val birthdayNamePoem = rootData?.birthdaynamepoem
//                val b = Bundle()
//                b.putString("catName", birthdayNamePoem?.name)
////                b.putParcelable("friendsList", birthdayNamePoem)
//                AppUtils.changeFragmentWithPositionFromHomeFrag(
//                    findNavController(), R.id.name_poem_frag, requireActivity(), b
//                )
            }

            b.nameOnBirthdayCake -> {
                val nameOnBirthdayCake = rootData?.nameonbirthdaycake
                val b = Bundle()
                b.putString("catName", nameOnBirthdayCake?.name)
                b.putParcelable("friendsList", nameOnBirthdayCake)
                b.putString("title", "Name On Cake")
                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(), R.id.gifsorryfrag, requireActivity(), b
                )
            }

            b.belated -> {
                val belatedList = rootData?.belated
                val b = Bundle()
                b.putString("catName", belatedList?.name)
                b.putString("title", "Belated")
                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(), R.id.cat_type_frag, requireActivity(), b
                )
            }

//            b.celebrities -> {
//                val events = rootData?.events
//                val b = Bundle()
//                AppUtils.changeFragmentWithPositionFromHomeFrag(
//                    findNavController(), R.id.action_nav_main_to_celebrities_main_frag, requireActivity(), b
//                )
//            }

            b.createCardsCv -> {
                val b = Bundle()
                b.putString("catName", "Birthday")
                b.putString("title", "Create Cards")
                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(), R.id.create_card_fg, requireActivity(), b
                )
            }

            b.birthdayInvitation -> {
                val b = Bundle()
                AppUtils.changeFragmentWithPositionFromHomeFrag(
                    findNavController(), R.id.invitationviewall_frag, requireActivity(), b
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title =
            resources.getString(R.string.mainscreen_title)
        super.onViewCreated(view, savedInstanceState)
    }

}