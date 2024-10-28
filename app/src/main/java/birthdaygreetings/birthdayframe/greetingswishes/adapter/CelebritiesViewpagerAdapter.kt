package birthdaygreetings.birthdayframe.greetingswishes.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import birthdaygreetings.birthdayframe.greetingswishes.fragment.CelebritiesFragment


class CelebritiesViewpagerAdapter(
    fragment: Fragment,
    val array: ArrayList<String>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return array.size
    }

    override fun createFragment(position: Int): Fragment {
//        println("eventByMonth HolidayViewpagerAdapter $position")
        return CelebritiesFragment(position, array.get(position))
    }
}