package edu.uc.kovaciad.slicetracker.services

import android.app.Application
import androidx.room.Room
import edu.uc.kovaciad.slicetracker.dao.IBrandDAO
import edu.uc.kovaciad.slicetracker.dao.SliceDatabase

//class MaterialService(application: Application) {
//    private val application = application
//
//    internal fun getMaterialDAO(): IMaterialDAO {
//        val db = Room.databaseBuilder(
//            application,
//            SliceDatabase::class.java, "mat-db"
//        ).build()
//        val materialDAO = db.materialDao()
//        return materialDAO
//}