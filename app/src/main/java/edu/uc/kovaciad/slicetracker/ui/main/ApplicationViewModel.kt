package edu.uc.kovaciad.slicetracker.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import edu.uc.kovaciad.slicetracker.services.BrandService
import edu.uc.kovaciad.slicetracker.services.ModelService
import edu.uc.kovaciad.slicetracker.services.PrinterService

class ApplicationViewModel(application: Application): AndroidViewModel(application) {
    private var _brandService: BrandService = BrandService(application)
    private var _modelService: ModelService = ModelService(application)
    private var _printerService: PrinterService = PrinterService(application)

    internal var brandService: BrandService
        get() {return _brandService}
        set(value) {_brandService = value}

    internal var modelService: ModelService
        get() {return _modelService}
        set(value) {_modelService = value}

    internal var printerService: PrinterService
        get() {return _printerService}
        set(value) {_printerService = value}
}