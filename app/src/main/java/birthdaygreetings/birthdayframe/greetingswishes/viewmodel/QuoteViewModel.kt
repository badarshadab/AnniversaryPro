package birthdaygreetings.birthdayframe.greetingswishes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import org.json.JSONArray
import java.io.File

class QuoteViewModel : ViewModel() {

    var quotes = MutableLiveData<List<String>>()
    private var quotesCache: HashMap<String, List<String>> = HashMap()
    private var lastCatName : String = ""

    fun getQuotes(catName: String) {
        if (catName == lastCatName) {
            // If the category name is the same as the last one, return the cached quotes
            quotes.postValue(quotesCache[catName] ?: ArrayList())
            return
        }

        lastCatName = catName // Update the last requested category name

        if (quotesCache.containsKey(catName)) {
            // If the quotes for this category are already cached, return them
            quotes.postValue(quotesCache[catName])
            return
        }

        quotes.value = ArrayList()
        println("getQuotes first line: $catName")
        val storage = Firebase.storage
        val listRef = storage.reference.child(catName)
        listRef.listAll()
            .addOnSuccessListener { listResult ->
                println("getQuotes assign value 2")
                val localFile = File.createTempFile("Quotes", "txt")
                if (listResult.items.isEmpty()) {
                    println("Twice Call change observer 1")
                    quotes.value = ArrayList()
                } else {
                    listResult.items[0].getFile(localFile).addOnSuccessListener {
                        val text = localFile.readText()
                        localFile.delete()
                        val array = JSONArray(text)
                        val list = ArrayList<String>()
                        for (i in 0 until array.length()) {
                            list.add(array[i].toString())
                        }
                        println("getQuotes assign value")
                        if (quotes.value?.isEmpty() == true) {
                            println("getQuotes assign value")
                            quotesCache[catName] = list // Cache the quotes for this category
                            quotes.postValue(list)
                        }
                    }
                        .addOnFailureListener {
                            println("Twice Call change observer 3")
                            quotes.value = ArrayList()
                        }
                }
            }
            .addOnFailureListener {
                println("Exception is $it")
                println("Twice Call change observer 4")
                quotes.value = ArrayList()
            }
    }



}