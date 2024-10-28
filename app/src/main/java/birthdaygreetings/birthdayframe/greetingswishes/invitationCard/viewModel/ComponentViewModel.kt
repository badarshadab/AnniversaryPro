package birthdaygreetings.birthdayframe.greetingswishes.invitationCard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.model.Component

class ComponentViewModel : ViewModel() {
    val componentList = MutableLiveData<List<Component>>()
    // function to send message
    fun setImage(componentShareModel: List<Component>) {
        componentList.value = componentShareModel
    }
}