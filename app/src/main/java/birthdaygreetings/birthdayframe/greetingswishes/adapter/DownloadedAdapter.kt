package birthdaygreetings.birthdayframe.greetingswishes.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.databinding.DownloadedListRowBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import birthdaygreetings.birthdayframe.greetingswishes.util.ShareUtils

class DownloadedAdapter(private var list: ArrayList<String>, var activity: Activity) :
    RecyclerView.Adapter<DownloadedAdapter.ImageHolder>() {

    var layoutInflater: LayoutInflater? = null
    var listener: CardClickListener? = null

    class ImageHolder(val b: DownloadedListRowBinding) : RecyclerView.ViewHolder(b.root) {

        fun setImage(path: String) {
            AppUtils.setImageWithRoundCorner(path, b.iv, 20, 700)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val b = DownloadedListRowBinding.inflate(layoutInflater!!, parent, false)
        return ImageHolder(b)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        var imageName = list[position]
        println("the value of image is  $imageName")
        holder.setImage(imageName)
        holder.b.card.setOnClickListener {
            listener?.onCardClick(it, list[position], position)
        }

        holder.b.actionLay.download.setCompoundDrawablesWithIntrinsicBounds(
            0,
            R.drawable.ic_delete,
            0,
            0
        )
        holder.b.actionLay.download.setText("Remove")
        holder.b.actionLay.download.setOnClickListener {
            ShareUtils.removeFromFavDialog(
                activity,
                list,
                position,
                imageName, this
            )
            notifyDataSetChanged()
        }
        holder.b.actionLay.share.setOnClickListener {
//            println("image url is " + position)
            ShareUtils.shareGIF(activity, list[position])
        }
        holder.b.actionLay.whatsapp.setOnClickListener {
            ShareUtils.shareGIFOnWhatsApp(activity, list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setImageList(list: ArrayList<String>) {
        this.list.clear()
//        println("list is shadab " + list)
        this.list.addAll(list)
        this.notifyDataSetChanged()

    }

    fun setClickListener(listener: CardClickListener) {
        this.listener = listener
    }

    interface CardClickListener {
        fun onCardClick(view: View, url: String, index: Int)
    }


}