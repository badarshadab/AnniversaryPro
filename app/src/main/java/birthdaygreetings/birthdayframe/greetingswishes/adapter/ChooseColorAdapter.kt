package birthdaygreetings.birthdayframe.greetingswishes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.model.ColorModel
import birthdaygreetings.birthdayframe.greetingswishes.util.OnItemClickListener


class ChooseColorAdapter(
    val list: List<ColorModel>,
    val context: Context,
    val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ChooseColorAdapter.MyViewHolder>() {

    class MyViewHolder(view: View, onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(view) {
        val colorview: ImageView = view.findViewById(R.id.colorimgview)
        val rowviewlayout: CardView = view.findViewById(R.id.rowcardview)

        init {
            view.setOnClickListener {
                onItemClickListener.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.colorlayout, parent, false)
        return MyViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (position == 0) {
            holder.colorview.setImageResource(R.drawable.colorimg)
        } else {
            holder.colorview.setBackgroundColor(list[position].color)
        }

    }
}