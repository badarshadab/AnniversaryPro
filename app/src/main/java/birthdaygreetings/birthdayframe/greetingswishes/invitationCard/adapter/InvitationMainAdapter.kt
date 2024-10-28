package poster.festival.greetings.sm.ui.invitationCard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import birthdaygreetings.birthdayframe.greetingswishes.databinding.InvitationMainRowBinding
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.model.InvitationModelItem

class InvitationMainAdapter : RecyclerView.Adapter<InvitationMainAdapter.MyViewHolder>() {

    private var invitationImageList = ArrayList<InvitationModelItem>()
    private lateinit var imageAdapter: InvitationImageAdapter
    private var invitationList = ArrayList<String>()
    private var imageList = ArrayList<String>()
    private var listener: InvitationClickListener? = null

    fun setCategories(invitationImageList: List<InvitationModelItem>) {
        this.invitationImageList.clear()
        this.invitationImageList.addAll(invitationImageList)
        notifyDataSetChanged()
    }

    fun setListener(listener: InvitationClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            InvitationMainRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        invitationList.clear()
        imageList.clear()
        imageAdapter = InvitationImageAdapter() { position1, catName, bImgUrl ->
            listener?.onEventItemClick(catName, position1, bImgUrl, position)
        }
        for (items in invitationImageList[position].template) {
            invitationList.add(items.sample_url)
            imageList.add(items.img_url)
        }
        imageAdapter.setImage(invitationList, invitationImageList[position].title, imageList)
        holder.b.rvInvitationRow.adapter = imageAdapter
        holder.b.tvInvitationTitle.text =
            invitationImageList[position].title + "(${invitationImageList[position].template.size})"
        holder.b.tvInvitationViewAll.setOnClickListener {
            listener?.onEventViewAllClick(position, invitationImageList[position].title)
        }
    }

    override fun getItemCount(): Int {
        return invitationImageList.size
    }

    class MyViewHolder(val b: InvitationMainRowBinding) : RecyclerView.ViewHolder(b.root)

    interface InvitationClickListener {
        fun onEventViewAllClick(catName: Int, title: String)
        fun onEventItemClick(catName: String, position: Int, bImgUrl: String, catPosition: Int)
    }
}