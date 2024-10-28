package birthdaygreetings.birthdayframe.greetingswishes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import birthdaygreetings.birthdayframe.greetingswishes.data.api.FirebaseHelper

class MyViewModelFactory(private val firebaseHelper: FirebaseHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StartEndViewModel::class.java)) {
            return StartEndViewModel(MyRepository(firebaseHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}