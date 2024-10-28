package birthdaygreetings.birthdayframe.greetingswishes.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.databinding.ContentPreviewRowBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.ShareUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.firebase.storage.StorageReference

class DailyWishesContentPreviewAdapter(
    val activity: Activity,
    val list: List<StorageReference>,
    val title: String
) :
    RecyclerView.Adapter<DailyWishesContentPreviewAdapter.ContentPreviewHolder>() {

    private var layoutInflater: LayoutInflater? = null
    private var rv: RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentPreviewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val contentPreviewRowBinding =
            ContentPreviewRowBinding.inflate(layoutInflater!!, parent, false)


        return ContentPreviewHolder(contentPreviewRowBinding)
    }

    override fun onBindViewHolder(holder: ContentPreviewHolder, position: Int) {
        holder.setData(position)
        var img = list[position]
        holder.b.share.setOnClickListener {

            img.downloadUrl.addOnSuccessListener {
                ShareUtils.shareGIF(
                    activity, it
                )
            }
        }
        holder.b.save.setOnClickListener {
            if (title.equals("Birthday Gif")) {
                img.downloadUrl.addOnSuccessListener {
                    ShareUtils.saveItem(activity, it, "Gifs")
                }
            } else {
                img.downloadUrl.addOnSuccessListener {
                    ShareUtils.saveItem(activity, it, "Cards")
                }
            }
        }
//        holder.b.save.setOnClickListener {
//            val img = list[position]
//            img.downloadUrl.addOnSuccessListener {
//                ShareUtils.saveItem(activity, it, type)
//            }
//        }
//
//
//        holder.b.share.setOnClickListener {
//            var img = list[position]
//            img.downloadUrl.addOnSuccessListener {
//                ShareUtils.shareGIF(
//                    activity, it
//                )
//            }
//
//        }
//        holder.b.actionLay.whatsapp.setOnClickListener {
//            var img = list[position]
//            img.downloadUrl.addOnSuccessListener {
//                ShareUtils.shareGIFOnWhatsApp(
//                    activity, it
//                )
//            }
//
//        }
//        val text = list[position].toString()
//        holder.b.tv.setText(text)
    }

//    fun setImageList(list: List<Any>) {
//        this.list.clear()
//        this.list.addAll(list)
//        notifyDataSetChanged()
//    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        rv = recyclerView
    }

    fun getTextView(index: Int): View? {
        val viewHolder = rv?.findViewHolderForAdapterPosition(index)
        viewHolder?.let {
            val holder = it as ContentPreviewHolder
            return holder.b.iv
        }
        return null
    }

    inner class ContentPreviewHolder(val b: ContentPreviewRowBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun setData(position: Int) {
            val img = list.get(position)
            img.downloadUrl.addOnSuccessListener {
                Glide.with(b.iv!!)
                    .load(it)
                    .transform(RoundedCorners(20))
                    .thumbnail(Glide.with(b.iv!!).load(R.drawable.loading_img))
                    .override(500)
                    .into(b.iv)
            }
            AppUtils.setImageWithRoundCorner(img, b.iv, 20, 500)
        }
    }
}