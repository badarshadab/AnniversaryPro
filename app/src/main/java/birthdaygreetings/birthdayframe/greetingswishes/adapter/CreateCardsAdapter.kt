package birthdaygreetings.birthdayframe.greetingswishes.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import birthdaygreetings.birthdayframe.greetingswishes.databinding.CreateCardsRowBinding
import birthdaygreetings.birthdayframe.greetingswishes.model.Family
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils

class CreateCardsAdapter(
    val activity: Activity,
    val model: Array<Family>,
    val mListener: RecyclerViewClickListener
) :
    RecyclerView.Adapter<CreateCardsAdapter.ContentPreviewHolder>() {

    private var layoutInflater: LayoutInflater? = null
    private var rv: RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentPreviewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val contentPreviewRowBinding =
            CreateCardsRowBinding.inflate(layoutInflater!!, parent, false)

        return ContentPreviewHolder(contentPreviewRowBinding)
    }

    override fun onBindViewHolder(holder: ContentPreviewHolder, position: Int) {
        val cIcon = model.get(position).icon
        val cName = model.get(position).name

        if (cIcon != null) {
            AppUtils.setImageWithRoundCorner(cIcon, holder.b.catIv, 20, 300)
        }
        holder.b.catName.text = cName

        holder.b.catIv.setOnClickListener { view ->
            mListener.onClick(view, position, cName)
        }


    }


    override fun getItemCount(): Int {
        return model.size
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        rv = recyclerView
    }


    inner class ContentPreviewHolder(val b: CreateCardsRowBinding) :
        RecyclerView.ViewHolder(b.root) {

    }

    interface RecyclerViewClickListener {
        fun onClick(view: View?, position: Int, catName: String?)
    }
}