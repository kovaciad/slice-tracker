package edu.uc.kovaciad.slicetracker.ui.main

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import edu.uc.kovaciad.slicetracker.dto.*

class MainViewModel : ViewModel() {
    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        listenToSliceFiles()
        listenToBrands()
        listenToModels()
        listenToPrinters()
        listenToMaterials()
        listenToMaterialTypes()
        listenToArtists()
    }


    private var _sliceFiles: MutableLiveData<ArrayList<SliceFile>> = MutableLiveData<ArrayList<SliceFile>>()
    private var _printers: MutableLiveData<ArrayList<Printer>> = MutableLiveData<ArrayList<Printer>>()
    private var _models: MutableLiveData<ArrayList<Model>> = MutableLiveData<ArrayList<Model>>()
    private var _brands: MutableLiveData<ArrayList<Brand>> = MutableLiveData<ArrayList<Brand>>()
    private var _materials: MutableLiveData<ArrayList<Material>> = MutableLiveData<ArrayList<Material>>()
    private var _materialTypes: MutableLiveData<ArrayList<MaterialType>> = MutableLiveData<ArrayList<MaterialType>>()
    private var _artists: MutableLiveData<ArrayList<Artist>> = MutableLiveData<ArrayList<Artist>>()

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

    private fun listenToMaterialTypes() {
        firestore.collection("material-types").addSnapshotListener {
                snapshot, e ->
            if (e != null) {
                Log.w(ContentValues.TAG, "Could not listen to Firebase")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val allMatTypes = ArrayList<MaterialType>()
                val docs = snapshot.documents
                docs.forEach {
                    val matType = it.toObject(MaterialType::class.java)
                    if (matType != null) {
                        matType.id = it.id
                        allMatTypes.add(matType)
                    }
                }
                _materialTypes.value = allMatTypes
            }
        }
    }

    private fun listenToArtists() {
        firestore.collection("artists").addSnapshotListener {
                snapshot, e ->
            if (e != null) {
                Log.w(ContentValues.TAG, "Could not listen to Firebase")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val allArtists = ArrayList<Artist>()
                val docs = snapshot.documents
                docs.forEach {
                    val artist = it.toObject(Artist::class.java)
                    if (artist != null) {
                        artist.id = it.id
                        allArtists.add(artist)
                    }
                }
                _artists.value = allArtists
            }
        }
    }

    /**
     * Save
     *
     * @param T: Type of data to be saved (Must extend IData)
     * @param item: Object of variable type to be saved to Firebase
     * @param collection: Collection for object to be stored in on Firebase (returns false if it DNE)
     * @author Aidan Kovacic
     * @return False if passed collection was not valid
     *
     * Valid collections: "slice-entries", "artists", "models", "materials", "material-types", "brands", "printers"
     */
    internal fun <T: IData> save(item: T, collection: String): Boolean {
        val validEntries: Array<String> = arrayOf("slice-entries", "artists", "models", "materials",
                                                "material-types", "brands", "printers")
        if (validEntries.contains(collection)) {
            Log.d("Firebase Save", "Invalid Collection Passed to save")
            return false
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
        val set = document.set(item)
        set.addOnSuccessListener {
            Log.d("Firebase", "Document Saved")

        }
        set.addOnFailureListener {
            Log.d("Firebase", "Save Failed")
        }
        return true
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

    internal var materialTypes: MutableLiveData<ArrayList<MaterialType>>
        get() {return _materialTypes}
        set(value) {_materialTypes = value}

    internal var artists: MutableLiveData<ArrayList<Artist>>
        get() {return _artists}
        set(value) {_artists = value}

}