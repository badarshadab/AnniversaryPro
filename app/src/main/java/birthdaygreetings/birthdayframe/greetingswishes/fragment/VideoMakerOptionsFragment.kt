package birthdaygreetings.birthdayframe.greetingswishes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import birthdaygreetings.birthdayframe.greetingswishes.databinding.VideomakeroptionsLayoutBinding


class VideoMakerOptionsFragment : Fragment(), View.OnClickListener {
    private lateinit var b: VideomakeroptionsLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = VideomakeroptionsLayoutBinding.inflate(inflater, container, false)
        b.editor.setOnClickListener(this)
        b.studio.setOnClickListener(this)
        return b.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "title"
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {
        when (v) {
            b.editor -> {

            }

            b.studio -> {

            }
        }
    }


}