package edu.uc.kovaciad.slicetracker.services

import android.app.Application
import androidx.room.Room
import edu.uc.kovaciad.slicetracker.dao.IModelDAO
import edu.uc.kovaciad.slicetracker.dao.SliceDatabase

class ModelService(application: Application) {
    private val application = application

    internal fun getModelDAO(): IModelDAO {
        val db = Room.databaseBuilder(
            application,
            SliceDatabase::class.java, "main-db"
        ).build()
        return db.modelDao()
    }
}