package poster.festival.greetings.sm.ui.invitationCard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import birthdaygreetings.birthdayframe.greetingswishes.databinding.InvitationImageRowBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class InvitationImageAdapter(private val clickListener: (position: Int, catName: String, bImgUrl: String) -> Unit) :
    RecyclerView.Adapter<InvitationImageAdapter.ImageViewHolder>() {

    var imageList = ArrayList<String>()
    var catName: String = ""
    var blankImgList = ArrayList<String>()
    private val limit = 5

    fun setImage(imageList: List<String>, catName: String, blankImgList: List<String>) {
        this.imageList.clear()
        this.imageList.addAll(imageList)
        this.blankImgList.addAll(blankImgList)
        this.catName = catName
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            InvitationImageRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(blankImgList[position]).into(holder.b.ivBlankRow)
        Glide.with(holder.itemView.context).load(imageList[position]).diskCacheStrategy(
            DiskCacheStrategy.ALL
        ).into(holder.b.ivRow)
        holder.itemView.setOnClickListener {
            clickListener(position, imageList[position], blankImgList[position])
        }
    }

    override fun getItemCount(): Int {
        if (imageList.size > limit) {
            return limit
        } else {
            return imageList.size
        }
    }

    class ImageViewHolder(val b: InvitationImageRowBinding) : RecyclerView.ViewHolder(b.root)
}