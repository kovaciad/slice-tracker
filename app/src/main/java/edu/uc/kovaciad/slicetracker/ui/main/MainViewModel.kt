package edu.uc.kovaciad.slicetracker.ui.main

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import edu.uc.kovaciad.slicetracker.dto.*
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class MainViewModel : ViewModel() {
    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        listenToSliceFiles()
    }


    private var _sliceFiles: MutableLiveData<ArrayList<SliceFile>> = MutableLiveData<ArrayList<SliceFile>>()

    /**
     * Called when initializing only. Starts firebase listening for slicefiles
     */
    private fun listenToSliceFiles() {
        firestore.collection("slice-entries").addSnapshotListener {
                snapshot, e ->
            if (e != null) {
                Log.w(ContentValues.TAG, "Could not listen to Firebase")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val allSlices = ArrayList<SliceFile>()
                val docs = snapshot.documents
                docs.forEach {
                    val slice = it.toObject(SliceFile::class.java)
                    if (slice != null) {
                        slice.id = it.id
                        allSlices.add(slice)
                    }
                }
                _sliceFiles.value = allSlices
            }
        }
    }


    /**
     * Save
     *
     * @param T: Type of data to be saved (Must extend IData)
     * @param item: Object of variable type to be saved to Firebase
     * @author Aidan Kovacic
     * @return True if successfully saved
     * Should be called in IO thread.
     */
// While we didn't end up using the robustness of this function, it is good for the future
    internal suspend fun <T: IData> save(item: T): Boolean {
        return try {
        // Assign the collection based on Data Type. (No longer used, but kept for future use)
        val collection = when (item) {
            is SliceFile -> "slice-entries"
            is Artist -> "artists"
            is Model -> "models"
            is Material -> "materials"
            is Brand -> "brands"
            is Printer -> "printers"
            // This should theoretically not be able to be reached
            else -> return false
        }

            val document =
                if (item.id.isNotEmpty()) {
                    // Update existing collection
                    firestore.collection(collection).document(item.id)
                } else {
                    // Create new collection
                    firestore.collection(collection).document()
                }
            item.id = document.id
            document.set(item).await()
            true
        } catch (e: Exception) {
            false
        }

    }

    /**
     * Deletes whatever data object is passed from Firebase.
     * @param item: item to be deleted. Must extend IData
     * @author Aidan Kovacic
     * @return True if object successfully deleted.
     */
    internal suspend fun <T:IData> delete(item:T): Boolean {
        return try {
            val collection = when (item) {
                is SliceFile -> "slice-entries"
                is Artist -> "artists"
                is Model -> "models"
                is Material -> "materials"
                is Brand -> "brands"
                is Printer -> "printers"
                // This should theoretically not be able to be reached
                else -> return false
            }
            val doc = firestore.collection(collection).document(item.id)
            doc.delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }

    internal var sliceFiles: MutableLiveData<ArrayList<SliceFile>>
        get() {return _sliceFiles}
        set(value) {_sliceFiles = value}

}