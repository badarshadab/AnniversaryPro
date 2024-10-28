package birthdaygreetings.birthdayframe.greetingswishes.ui.createCard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.StorageReference
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.databinding.CardGifRowBinding

class CardBGAdapter(private val clickListener: (position: Int) -> Unit) :
    RecyclerView.Adapter<CardBGAdapter.MyViewHolder>() {

    private var backgroundList = ArrayList<StorageReference>()

    fun setBackgroundList(backgroundList: List<StorageReference>) {
        this.backgroundList.clear()
        this.backgroundList.addAll(backgroundList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CardGifRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(backgroundList[position])
            .thumbnail(Glide.with(holder.itemView.context).load(R.drawable.loading_img))
            .error(R.drawable.error)
            .into(holder.b.catImg)
        holder.itemView.setOnClickListener {
            clickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return backgroundList.size
    }

    class MyViewHolder(val b: CardGifRowBinding) : RecyclerView.ViewHolder(b.root) {
    }
}