package birthdaygreetings.birthdayframe.greetingswishes.modelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import birthdaygreetings.birthdayframe.greetingswishes.data.api.FirebaseHelper
import birthdaygreetings.birthdayframe.greetingswishes.viewmodel.HomeViewModel
import birthdaygreetings.birthdayframe.greetingswishes.viewmodel.MyRepository

class MyViewModelFactory(private val firebaseHelper: FirebaseHelper) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(MyRepository(firebaseHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}