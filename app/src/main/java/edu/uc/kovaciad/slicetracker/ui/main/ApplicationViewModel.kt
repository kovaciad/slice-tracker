package edu.uc.kovaciad.slicetracker.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import edu.uc.kovaciad.slicetracker.services.DatabaseService

class ApplicationViewModel(application: Application): AndroidViewModel(application) {
    private var _databaseService: DatabaseService = DatabaseService(application)

    internal var databaseService: DatabaseService
        get() {return _databaseService}
        set(value) {_databaseService = value}

}