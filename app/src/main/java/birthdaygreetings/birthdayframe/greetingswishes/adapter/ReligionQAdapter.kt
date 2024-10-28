package birthdaygreetings.birthdayframe.greetingswishes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import birthdaygreetings.birthdayframe.greetingswishes.databinding.QuotesRowBinding
import birthdaygreetings.birthdayframe.greetingswishes.model.Religiou
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils

class ReligionQAdapter(
    val model: Array<Religiou>,
    val mListener: RecyclerViewClickListener
) :
    RecyclerView.Adapter<ReligionQAdapter.ContentPreviewHolder>() {

    private var layoutInflater: LayoutInflater? = null
    private var rv: RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentPreviewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val contentPreviewRowBinding =
            QuotesRowBinding.inflate(layoutInflater!!, parent, false)

        return ContentPreviewHolder(contentPreviewRowBinding)
    }

    override fun onBindViewHolder(holder: ContentPreviewHolder, position: Int) {

        val cIcon = model.get(position).icon
        val cName = model[position].name
        if (cIcon != null) {
            AppUtils.setImageWithRoundCorner(cIcon, holder.b.catIv, 20, 300)
        }
        holder.b.catName.text = cName
        holder.b.mainLay.setOnClickListener { v ->
            mListener.onClick(v, position, "")
        }

    }


    override fun getItemCount(): Int {
        return model.size
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        rv = recyclerView
    }


    inner class ContentPreviewHolder(val b: QuotesRowBinding) :
        RecyclerView.ViewHolder(b.root) {

    }

    interface RecyclerViewClickListener {
        fun onClick(view: View?, position: Int, catName: String?)
    }
}