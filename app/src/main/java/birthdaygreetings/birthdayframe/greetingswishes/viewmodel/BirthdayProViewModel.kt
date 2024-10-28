package birthdaygreetings.birthdayframe.greetingswishes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import birthdaygreetings.birthdayframe.greetingswishes.model.RootNew

class BirthdayProViewModel : ViewModel() {

    val repositoryResponseLiveData = MutableLiveData<RootNew?>()
//    private val _allFrameListLiveData = MutableLiveData<List<Root>>()
//    val repositoryResponseLiveData_ImageStore = MutableLiveData<List<StorageReference>>()
//    val allFrameListLiveData: MutableLiveData<List<Root>> get() = _allFrameListLiveData // Expose it as LiveData

    fun getComModel() {
//        viewModelScope.launch {
        val database: DatabaseReference = Firebase.database.reference

        // Check if repositoryResponseLiveData.value is null
        if (repositoryResponseLiveData.value == null) {
            val postListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    try {
                        repositoryResponseLiveData.value =
                            dataSnapshot.getValue(RootNew::class.java)
//                        viewModelScope.launch {
//                            repositoryResponseLiveData.value?.family?.get(0)
//                        }
                        print("repositoryResponseLiveData.value " + repositoryResponseLiveData.value)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("Shadab Database from Firebase " + databaseError.message)
                }

            }
            database.addValueEventListener(postListener)
        } else {
            // Now, if repositoryResponseLiveData.value is not null, use its value
//            repositoryResponseLiveData.value?.greetings?.get(pos)?.let {
////                loadAllFrameList("greetings/${it.name}" , pos)
//                it.subitems?.let { it1 -> loadAllFrameList(it1,it.name, pos) }
//                println("folderPath: greetings/${it.name}")
//            }
        }
//        }
    }
//    public fun loadAllFrameList(list: List<Subitem> ,folderPath: String , pos: Int) {
//        var allFrameList_Help = ArrayList<CardList>()
//        val locale = "IN"
//        viewModelScope.launch {
//            val storage = Firebase.storage
//            var count = 0
//            var maxFolder = list.size
//            for (subItem in list){
//
//                    val itemPath = "greetings/" + folderPath + "/" + subItem.name
//                    val listRef = storage.reference.child(itemPath)
//                    listRef.listAll().addOnSuccessListener { listResult ->
//                        val frameN = CardList(listRef.name, listResult.items)
//                        if(locale.equals(subItem.locale)) {
//                            allFrameList_Help.add(frameN)
//                        }
//                        count++
//                        println("Debug frames 3 " + count + " maxFolder " + maxFolder)
//                        if (count == maxFolder) {
//                            _allFrameListLiveData.postValue(allFrameList_Help)
//                        }
//                    }
//                }
//        }
//    }
}