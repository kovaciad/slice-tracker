package edu.uc.kovaciad.slicetracker.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ApplicationViewModel(application: Application) : AndroidViewModel(application) {
    private val locationLiveData = LocationLiveData(application)
    internal fun getLocationLiveData() = locationLiveData

}