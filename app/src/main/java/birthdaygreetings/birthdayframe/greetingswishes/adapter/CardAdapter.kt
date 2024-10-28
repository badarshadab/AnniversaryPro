package birthdaygreetings.birthdayframe.greetingswishes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.OnItemClickListener_Gif
import com.bumptech.glide.Glide
import com.google.firebase.storage.StorageReference

class CardAdapter(
    var list: List<StorageReference>,
    var context: Context,
    private val onitemclicklistenerGif: OnItemClickListener_Gif
) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    inner class ViewHolder(view: View, onitemclicklistenerGif: OnItemClickListener_Gif) :
        RecyclerView.ViewHolder(view) {
        val fest_imageView: ImageView = itemView.findViewById(R.id.gifImgView)

        init {
            itemView.setOnClickListener {
                onitemclicklistenerGif.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.gif_and_card_row_item, parent, false)
        return ViewHolder(view, onitemclicklistenerGif)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        AppUtils.setImageWithRoundCorner(list.get(position), holder.fest_imageView, 20, 200)

//        Glide
//            .with(context)
//            .load(list[position])
//            .placeholder(R.drawable.loading_img)
//            .error(R.drawable.error_img)
//            .into(holder.fest_imageView)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}