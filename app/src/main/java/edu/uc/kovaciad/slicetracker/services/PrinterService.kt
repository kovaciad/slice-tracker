package edu.uc.kovaciad.slicetracker.services

import android.app.Application
import androidx.room.Room
import edu.uc.kovaciad.slicetracker.dao.IPrinterDAO
import edu.uc.kovaciad.slicetracker.dao.SliceDatabase

class PrinterService(application: Application) {
    private val application = application

    internal fun getPrinterDAO(): IPrinterDAO {
        val db = Room.databaseBuilder(
            application,
            SliceDatabase::class.java, "main-db"
        ).build()
        return db.printerDao()
    }
}