package edu.uc.kovaciad.slicetracker.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.uc.kovaciad.slicetracker.MainActivity
import edu.uc.kovaciad.slicetracker.dao.IBrandDAO
import edu.uc.kovaciad.slicetracker.dto.Brand
import edu.uc.kovaciad.slicetracker.dto.Material
import edu.uc.kovaciad.slicetracker.dto.Model
import edu.uc.kovaciad.slicetracker.dto.Printer

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var brands = MutableLiveData<ArrayList<Brand>>()
    var models = MutableLiveData<ArrayList<Model>>()
    var printers = MutableLiveData<ArrayList<Printer>>()

    fun fetchBrands() {
        val db = MainActivity.db
        val brandDao = db!!.brandDao()
        brands.value = brandDao.getAll()
    }
    fun fetchModels() {
        val db = MainActivity.db
        val modelDao = db!!.modelDao()
        models.value = modelDao.getAll()
    }
    fun fetchPrinters() {
        val db = MainActivity.db
        val printerDao = db!!.printerDao()
        printers.value = printerDao.getAll()
    }

}