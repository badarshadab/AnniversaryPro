package birthdaygreetings.birthdayframe.greetingswishes.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.databinding.RecyclerGifImgRowBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.firebase.storage.StorageReference

class GifImageAdapter(
    val activity: Activity,
    val list: List<StorageReference>,
    private var mListener: RecyclerViewClickListener? = null
) :
    RecyclerView.Adapter<GifImageAdapter.ContentPreviewHolder>() {

    private var layoutInflater: LayoutInflater? = null
    private var rv: RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentPreviewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val recyclerGifImgRowBinding =
            RecyclerGifImgRowBinding.inflate(layoutInflater!!, parent, false)

//        println("list.size Shadab $list")
//        println("Shadab $list")

        return ContentPreviewHolder(recyclerGifImgRowBinding)
    }

    override fun onBindViewHolder(holder: ContentPreviewHolder, position: Int) {
        holder.setData(position)
        holder.b.gifImgView.setOnClickListener { v ->
            mListener?.onClick(v, position)
        }
    }


    override fun getItemCount(): Int {
        println("list.size $list.size")
        return list.size

    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        rv = recyclerView
    }


    inner class ContentPreviewHolder(val b: RecyclerGifImgRowBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun setData(position: Int) {
            var mList = list
            AppUtils.setImage(b.gifImgView , mList[position])
//            mList[position].downloadUrl.addOnSuccessListener {
//                Glide.with(b.gifImgView!!)
//                    .load(it)
//                    .transform(RoundedCorners(20))
//                    .thumbnail(Glide.with(b.gifImgView!!).load(R.drawable.loading_img))
//                    .override(500)
//                    .into(b.gifImgView)
//            }
//            AppUtils.setImage(b.gifImgView , list.get(position))
//            AppUtils.setImageWithRoundCorner(list.get(position), b.gifImgView, 20, 500)
        }
    }

    interface RecyclerViewClickListener {
        fun onClick(view: View?, position: Int)
    }
}