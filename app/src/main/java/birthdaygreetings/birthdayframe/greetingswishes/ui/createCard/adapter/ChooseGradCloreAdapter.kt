package birthdaygreetings.birthdayframe.greetingswishes.ui.createCard.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.model.GradientModel
import birthdaygreetings.birthdayframe.greetingswishes.util.OnItemClickListener

class ChooseGradCloreAdapter(
    val gradlist: ArrayList<GradientModel>,
    val context: Context,
    val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<ChooseGradCloreAdapter.MyViewHolder>() {
    class MyViewHolder(view: View, onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(view) {
        var gradcolor: ImageView = view.findViewById(R.id.colorimgview)

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
        return gradlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val gd =
            GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, gradlist[position].gradlist)
        gd.cornerRadius = 0f
        holder.gradcolor.setBackgroundDrawable(gd);
    }
}


