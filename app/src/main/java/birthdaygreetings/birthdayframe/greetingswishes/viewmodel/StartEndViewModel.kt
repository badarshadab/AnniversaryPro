package birthdaygreetings.birthdayframe.greetingswishes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers

class StartEndViewModel(private val myRepository: MyRepository):ViewModel() {
    var categoryName: String = ""
    var listStorage: MutableLiveData<Resource<List<StorageReference>>>? = null
    fun loadImagesStorage(catName: String): MutableLiveData<Resource<List<StorageReference>>> {
        if (!catName.equals(categoryName)) {
            listStorage =
                loadImagesStorageOnce(catName) as MutableLiveData<Resource<List<StorageReference>>>
        }
        return listStorage as MutableLiveData<Resource<List<StorageReference>>>
    }

    private fun loadImagesStorageOnce(catName: String) = liveData(Dispatchers.IO) {
        categoryName = catName
        emit(Resource.loading(data = null))
        try {
            val response = myRepository.loadImagesStorage(catName)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }
}