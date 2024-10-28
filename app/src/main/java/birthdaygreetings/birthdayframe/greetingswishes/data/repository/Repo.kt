package birthdaygreetings.birthdayframe.greetingswishes.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import birthdaygreetings.birthdayframe.greetingswishes.data.model.HomeModel

object Repo {
    private lateinit var database: DatabaseReference
    fun loadData(homeModel: MutableLiveData<HomeModel>) {
        database = Firebase.database.reference

        val postList = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

//                try {
                println("OnDataChange ")

                homeModel.value = dataSnapshot.getValue(HomeModel::class.java)
                println("BIHAR2 $dataSnapshot")
                /* }catch (e:Exception){
                     e.printStackTrace()
                 }*/
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("ErrorOnFirebase " + databaseError.message)
            }
        }
        database.addValueEventListener(postList)
    }


    val storage: FirebaseStorage = FirebaseStorage.getInstance()
    fun fetchAllFrames(frameList: MutableLiveData<List<StorageReference>>, path: String) {
        val listRef = storage.reference.child(path)
        listRef.listAll().addOnSuccessListener { listResult ->
            frameList.postValue(listResult.items)
            println("RAJRONNAK  ${listResult.items.toString()}")
        }
            .addOnFailureListener { }
    }

    val storage2: FirebaseStorage = FirebaseStorage.getInstance()
    fun fetchAllSticker(frameList: MutableLiveData<List<StorageReference>>, path: String) {
        val listRef = storage2.reference.child(path)
        listRef.listAll()
            .addOnSuccessListener { listResult ->
                frameList.postValue(listResult.items)
                println("BIHAR  ${listResult.items}")
            }
            .addOnFailureListener { }
    }

}







