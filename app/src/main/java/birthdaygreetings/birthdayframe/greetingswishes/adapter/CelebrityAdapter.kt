package birthdaygreetings.birthdayframe.greetingswishes.adapter

import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.databinding.CelebrityRowLayoutBinding
import birthdaygreetings.birthdayframe.greetingswishes.model.Event
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils

class CelebrityAdapter(val activity: Activity, val msgList: ArrayList<Event>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var layoutInflater: LayoutInflater? = null

    class HolidayViewHolder(val b: CelebrityRowLayoutBinding, val activity: Activity) :
        RecyclerView.ViewHolder(b.root) {

        fun ViewHolder(itemView: View) {

            val typeface = Typeface.createFromAsset(
                activity.getAssets(),
                "fonts/ALEO-REGULAR.OTF"
            )
            b.quotesText.setTypeface(typeface)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val b = CelebrityRowLayoutBinding.inflate(layoutInflater!!, parent, false)
        return HolidayViewHolder(b, activity)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val holder = holder as HolidayViewHolder
        val festival_name: String = msgList!![position].name.toString()
        val date = msgList!![position].udate.toString()
        val info = msgList!![position].info.toString()

        holder.b.quotesText.text = festival_name
        holder.b.dateofbirth.text = date
//        holder.b.dateText.setText((date))
        var img_addrs = msgList!![position].icon.toString()
        AppUtils.setImageWithRoundCorner(img_addrs, holder.b.iv, 40, 200)
        holder.itemView.setOnClickListener(View.OnClickListener { v: View? ->
            val b = Bundle()
            b.putInt("pos", position)
            b.putString("trending_cat", festival_name)
            b.putString("img_addrs", img_addrs)
            b.putString("celeb_name", festival_name)
            b.putString("date", date)
            b.putString("info", info)
            AppUtils.changeFragment(
                activity,
                R.id.action_celebrities_main_frag_to_celebrities_prev_frag,
                b
            )
        })
    }


    override fun getItemCount(): Int {
        return msgList!!.size
    }


}