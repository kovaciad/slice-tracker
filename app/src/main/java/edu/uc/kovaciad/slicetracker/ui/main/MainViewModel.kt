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
        listenToBrands()
        listenToModels()
        listenToPrinters()
        listenToMaterials()
    }


    private var _sliceFiles: MutableLiveData<ArrayList<SliceFile>> = MutableLiveData<ArrayList<SliceFile>>()
    private var _printers: MutableLiveData<ArrayList<Printer>> = MutableLiveData<ArrayList<Printer>>()
    private var _models: MutableLiveData<ArrayList<Model>> = MutableLiveData<ArrayList<Model>>()
    private var _brands: MutableLiveData<ArrayList<Brand>> = MutableLiveData<ArrayList<Brand>>()
    private var _materials: MutableLiveData<ArrayList<Material>> = MutableLiveData<ArrayList<Material>>()

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

    private fun listenToPrinters() {
        firestore.collection("printers").addSnapshotListener {
                snapshot, e ->
            if (e != null) {
                Log.w(ContentValues.TAG, "Could not listen to Firebase")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val allPrinters = ArrayList<Printer>()
                val docs = snapshot.documents
                docs.forEach {
                    val printer = it.toObject(Printer::class.java)
                    if (printer != null) {
                        printer.id = it.id
                        allPrinters.add(printer)
                    }
                }
                _printers.value = allPrinters
            }
        }
    }

    private fun listenToModels() {
        firestore.collection("models").addSnapshotListener {
                snapshot, e ->
            if (e != null) {
                Log.w(ContentValues.TAG, "Could not listen to Firebase")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val allModels = ArrayList<Model>()
                val docs = snapshot.documents
                docs.forEach {
                    val model = it.toObject(Model::class.java)
                    if (model != null) {
                        model.id = it.id
                        allModels.add(model)
                    }
                }
                _models.value = allModels
            }
        }
    }

    private fun listenToBrands() {
        firestore.collection("brands").addSnapshotListener {
                snapshot, e ->
            if (e != null) {
                Log.w(ContentValues.TAG, "Could not listen to Firebase")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val allBrands = ArrayList<Brand>()
                val docs = snapshot.documents
                docs.forEach {
                    val brand = it.toObject(Brand::class.java)
                    if (brand != null) {
                        brand.id = it.id
                        allBrands.add(brand)
                    }
                }
                _brands.value = allBrands
            }
        }
    }

    private fun listenToMaterials() {
        firestore.collection("materials").addSnapshotListener {
                snapshot, e ->
            if (e != null) {
                Log.w(ContentValues.TAG, "Could not listen to Firebase")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val allMats = ArrayList<Material>()
                val docs = snapshot.documents
                docs.forEach {
                    val material = it.toObject(Material::class.java)
                    if (material != null) {
                        material.id = it.id
                        allMats.add(material)
                    }
                }
                _materials.value = allMats
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

    internal suspend fun <T: IData> save(item: T): Boolean {
        return try {
        // Assign the collection based on Data Type.
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


    internal var sliceFiles: MutableLiveData<ArrayList<SliceFile>>
        get() {return _sliceFiles}
        set(value) {_sliceFiles = value}

    internal var printers: MutableLiveData<ArrayList<Printer>>
        get() {return _printers}
        set(value) {_printers = value}

    internal var models: MutableLiveData<ArrayList<Model>>
        get() {return _models}
        set(value) {_models = value}

    internal var brands: MutableLiveData<ArrayList<Brand>>
        get() {return _brands}
        set(value) {_brands = value}

    internal var materials: MutableLiveData<ArrayList<Material>>
        get() {return _materials}
        set(value) {_materials = value}


}