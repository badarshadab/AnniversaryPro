package birthdaygreetings.birthdayframe.greetingswishes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birthdaygreetings.birthdayframe.greetingswishes.model.CardShareModel

class IVCardShareViewModel : ViewModel() {
    val shareImages = MutableLiveData<CardShareModel>()

    // function to send message
    fun setImage(posterShareModel: CardShareModel) {
        shareImages.value = posterShareModel
    }
}