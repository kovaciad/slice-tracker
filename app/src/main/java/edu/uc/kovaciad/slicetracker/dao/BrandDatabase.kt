package edu.uc.kovaciad.slicetracker.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.uc.kovaciad.slicetracker.dto.Brand

@Database(entities = arrayOf(Brand::class), version=1)
abstract class BrandDatabase : RoomDatabase() {
    abstract fun brandDao(): IBrandDAO

}