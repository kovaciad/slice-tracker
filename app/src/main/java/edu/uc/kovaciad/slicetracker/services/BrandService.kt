package edu.uc.kovaciad.slicetracker.services

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.uc.kovaciad.slicetracker.dao.IBrandDAO
import edu.uc.kovaciad.slicetracker.dao.SliceDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class BrandService(application: Application) {
    private val application = application

    internal fun getBrandDAO(): RoomDatabase {
        val db = Room.databaseBuilder(
            application,
            SliceDatabase::class.java, "main-db"
        )
            .addCallback(object : RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                }
            })
            .build()
        return db
    }

}