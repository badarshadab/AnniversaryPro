package birthdaygreetings.birthdayframe.greetingswishes.ui.createCard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.StorageReference
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.util.OnItemClickListener

class AddbgCardAdapter(
    var list: List<StorageReference>,
    val context: Context,
    val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<AddbgCardAdapter.MyViewHolder>() {

    class MyViewHolder(view: View, onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(view) {
        val gmimageview: ImageView = itemView.findViewById(R.id.addbgimageview)

        init {
            itemView.setOnClickListener {
                onItemClickListener.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.addbgcarditem, parent, false)
        return MyViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(list[position])
            .thumbnail(Glide.with(context).load(R.drawable.loading_img))
            .error(R.drawable.error)
            .into(holder.gmimageview);

    }
}