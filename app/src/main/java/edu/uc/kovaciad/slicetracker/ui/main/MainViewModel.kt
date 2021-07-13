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

class MainViewModel {
    //MutableLiveData array list
    var material: MutableLiveData<Material> = MutableLiveData<Material>()
    var brand: MutableLiveData<Brand> = MutableLiveData<Brand>()

    fun fetchProduct(myMATERIAL: String) {

    }
}