package birthdaygreetings.birthdayframe.greetingswishes.ui.createCard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import birthdaygreetings.birthdayframe.greetingswishes.data.model.CreateCard
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.databinding.CreatecardRowitemBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.OnItemClickListener

class CardCatAdapter(
    var list: ArrayList<CreateCard>,
    var context: Context,
    private val onitemclicklistener: OnItemClickListener
) : RecyclerView.Adapter<CardCatAdapter.ViewHolder>() {

    inner class ViewHolder(
        var bind: CreatecardRowitemBinding,
        onitemclicklistener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(bind.root) {
        //        val fest_imageView: ImageView = itemView.findViewById(R.id.row_item_gif_card)
        init {
            itemView.setOnClickListener {
                onitemclicklistener.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        /*val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.createcard_rowitem, parent, false)*/
        return ViewHolder(
            CreatecardRowitemBinding.inflate(LayoutInflater.from(parent.context)),
            onitemclicklistener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        var data = list[position].name!!.startsWith(" ")
//
//        println(" MY Adapter text :-> ${data}")
//        if(holder.bind.textid.text.isEmpty() || detailsText.isEmpty() || list[position].name!!.startsWith(" ") || detailsText.startsWith(" ")){

// perform operation.

//        }

//        holder.bind.textid.text = list[position].name
        Glide.with(context).load(list[position].icon)
            .thumbnail(Glide.with(context).load(R.drawable.loading_img))
            .error(R.drawable.error)
            .into(holder.bind.catIv);
    }

    override fun getItemCount(): Int {
        return list.size
    }
}