package birthdaygreetings.birthdayframe.greetingswishes.viewmodel

import birthdaygreetings.birthdayframe.greetingswishes.data.api.FirebaseHelper

class MyRepository(private val firebaseHelper: FirebaseHelper) {

    suspend fun loadRealTimeData() = firebaseHelper.loadRealTimeData()

    suspend fun loadImagesStorage(catName: String) = firebaseHelper.loadImagesStorage(catName)

}