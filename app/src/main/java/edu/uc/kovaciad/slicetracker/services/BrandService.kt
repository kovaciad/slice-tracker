package edu.uc.kovaciad.slicetracker.services

import android.app.Application
import androidx.room.Room
import edu.uc.kovaciad.slicetracker.dao.IBrandDAO
import edu.uc.kovaciad.slicetracker.dao.SliceDatabase

class BrandService(application: Application) {
    private val application = application

    internal fun getBrandDAO(): IBrandDAO {
        val db = Room.databaseBuilder(
            application,
            SliceDatabase::class.java, "main-db"
        ).build()
        return db.brandDao()
    }
}