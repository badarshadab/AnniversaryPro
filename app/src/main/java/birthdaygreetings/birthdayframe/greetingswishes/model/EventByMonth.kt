package com.greetings.allwishes.ui.model

import androidx.annotation.Keep
import birthdaygreetings.birthdayframe.greetingswishes.model.Event


@Keep
class EventByMonth {
    var date: String? = null
    var monthName: String? = null
    var events: ArrayList<Event>? = null
}