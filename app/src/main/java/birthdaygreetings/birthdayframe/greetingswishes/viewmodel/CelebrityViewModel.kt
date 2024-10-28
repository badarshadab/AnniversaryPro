package birthdaygreetings.birthdayframe.greetingswishes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import birthdaygreetings.birthdayframe.greetingswishes.model.Event
import birthdaygreetings.birthdayframe.greetingswishes.model.RootNew
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.greetings.allwishes.ui.model.EventByMonth
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Collections
import java.util.Date

class CelebrityViewModel() : ViewModel() {

    var comModel: MutableLiveData<RootNew?>? = null
    private var arrayList: ArrayList<EventByMonth>? = null
    private var _arrayListLiveData = MutableSharedFlow<ArrayList<EventByMonth>>()
    val arrayListLiveData: SharedFlow<ArrayList<EventByMonth>> = _arrayListLiveData
    lateinit var date: String
    lateinit var eventslist: ArrayList<Event>

    fun getComModel(): ArrayList<EventByMonth>? {
        if (comModel == null) {
            comModel = MutableLiveData()
            loadCommonData()
            return null
        } else {
            return arrayList
        }
    }

    private fun setArrayListLive(arrayList: ArrayList<EventByMonth>) {

        viewModelScope.launch {
            _arrayListLiveData?.emit(arrayList)
        }
    }


    private fun loadCommonData() {
        val database: DatabaseReference = Firebase.database.reference
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                try {
                    val model = dataSnapshot.getValue(RootNew::class.java)
                    comModel?.value = model
                    arrayList?.clear()
                    eventslist = model?.events!!
                    viewModelScope.launch {

                        Collections.sort(eventslist, PriorityComparator())


                        for (events in eventslist) {
                            date = events.udate?.let { getMonthDigit(it) }.toString()
                            if (date.equals("-1")) {

                            } else {
                                val eventByMonth = searchEvent(date)
                                addEventByMonth(eventByMonth, events, Integer.parseInt(date))
                            }

                        }
                        arrayList?.let { setArrayListLive(it) }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }


            override fun onCancelled(databaseError: DatabaseError) {
                println("Anshu  Database from fiebase" + databaseError.message)
            }
        }
        database.addValueEventListener(postListener)
    }

    fun getMonthDigit(str: String): String {
        val asubstring = str.substring(3, 5)
        println("asubstring is  " + str)
        val milli = milliseconds(str)

        val oneYearAfter = getCurrentMonthMillis() + 31536000000

        println("time 1 oneYearAfter " + oneYearAfter)
        println("time 2 milli " + milli)
        println("time 3 getCurrentMonthMillis " + getCurrentMonthMillis())

        if (milli > getCurrentMonthMillis() && milli < oneYearAfter) {
            println("milli asubstring " + milli)
            return asubstring
        } else {
            return "-1"
        }
    }

    fun getSString(str: String): String {
        val asubstring = str.substring(3, 5)
        println("asubstring is  " + str)
        val milli = milliseconds(str)
        if (milli > getCurrentMonthMillis()) {
            println("milli asubstring " + milli)
            return asubstring
        } else {
            return "-1"
        }
    }

    private fun getCurrentMonthMillis(): Long {
        val c: Calendar = Calendar.getInstance() // this takes current date

        c.set(Calendar.DAY_OF_MONTH, 1)
        c.set(Calendar.DAY_OF_MONTH, 0)
        c.set(Calendar.HOUR_OF_DAY, 0)
        return c.timeInMillis
    }

    fun milliseconds(date: String?): Long {
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        try {
            val mDate: Date = sdf.parse(date)
            val timeInMilliseconds = mDate.time
            return timeInMilliseconds
        } catch (e: ParseException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        return 0
    }

    private fun addEventByMonth(eventByMonth: EventByMonth?, event: Event, monthNo: Int) {
        if (eventByMonth == null) {
            var eventByMonth = EventByMonth()
            eventByMonth.date = event.udate
            eventByMonth.monthName = convertMonthDigitToName(monthNo)
            eventByMonth.events = ArrayList()
            eventByMonth.events?.add(event)
            if (arrayList == null) {
                arrayList = ArrayList()
            }
            arrayList?.add(eventByMonth)

        } else {
            eventByMonth.events?.add(event)
        }
    }

    fun convertMonthDigitToName(monthNo: Int): String {
        println("month in  convertMonth" + monthNo)
        var monthName: String
        monthName = when (monthNo) {
            1 -> "January"
            2 -> "February"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            12 -> "December"
            else -> ""
        }
        return monthName


    }

    private fun searchEvent(currentM: String): EventByMonth? {
        if (arrayList == null) {
            return null
        }
        for (eventByMonth in arrayList!!) {
            val monthPart = eventByMonth.date?.let { getSString(it) }
            if (monthPart.equals(currentM)) {
                return eventByMonth
            }
        }
        return null
    }

    private class PriorityComparator : Comparator<Event> {
        override fun compare(o1: Event?, o2: Event?): Int {
            val date1 = milliseconds(o1?.udate)
            val date2 = milliseconds(o2?.udate)
            //For Ascending Order
            return date1.compareTo(date2);
        }

        private fun milliseconds(date: String?): Long {
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            try {
                val mDate: Date = sdf.parse(date)
                val timeInMilliseconds = mDate.time
                return timeInMilliseconds
            } catch (e: ParseException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }
            return 0
        }
    }


}