package birthdaygreetings.birthdayframe.greetingswishes.adapter

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import birthdaygreetings.birthdayframe.greetingswishes.fragment.SavedFragment

class SavedViewpagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val activity: Activity,
    val array: Array<String>,
    val clickedType: String
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return array.size
    }

    override fun createFragment(position: Int): Fragment {
        println("eventByMonth HolidayViewpagerAdapter $position")
        return SavedFragment(position , clickedType)
    }
}