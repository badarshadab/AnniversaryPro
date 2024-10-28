package birthdaygreetings.birthdayframe.greetingswishes.util

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import birthdaygreetings.birthdayframe.greetingswishes.adapter.ImageAdapterDailyWishes

//import com.sm.allwishes.greetings.ui.adapter.ImageAdapter

class CardRecyclerview : RecyclerView {

    constructor(context: Context) : super(context) {
        init()
    }

    //
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = ImageAdapterDailyWishes()
    }

    //
    fun getMAdapter(): ImageAdapterDailyWishes {
        return adapter as ImageAdapterDailyWishes
    }

    fun getCallerID(callerID: String) {
        getMAdapter().getCaller(callerID)
    }

    fun getType(type: String) {
        getMAdapter().getType(type)
    }


    fun setData(list: List<Any>) {
        getMAdapter().setImageList(list)
    }


    fun setItemClickListener(listener: ImageAdapterDailyWishes.ImageClickListener) {
        getMAdapter().setClickListener(listener)
    }

}