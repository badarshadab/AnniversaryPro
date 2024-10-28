package birthdaygreetings.birthdayframe.greetingswishes.invitationCard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import birthdaygreetings.birthdayframe.greetingswishes.databinding.InvitationViewAllRowBinding
import com.bumptech.glide.Glide

class InvitationViewAllAdapter(val clickListener: (position: Int) -> Unit) :
    RecyclerView.Adapter<InvitationViewAllAdapter.MyViewHolder>() {

    private var invitationImageList = ArrayList<String>()

    fun setCategories(invitationImageList: List<String>) {
        this.invitationImageList.clear()
        this.invitationImageList.addAll(invitationImageList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            InvitationViewAllRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(invitationImageList[position])
            .into(holder.b.ivInvitation)
        holder.itemView.setOnClickListener {
            clickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return invitationImageList.size
    }

    class MyViewHolder(val b: InvitationViewAllRowBinding) : RecyclerView.ViewHolder(b.root)
}