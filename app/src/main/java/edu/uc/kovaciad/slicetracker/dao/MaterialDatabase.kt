package edu.uc.kovaciad.slicetracker.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.uc.kovaciad.slicetracker.dto.Material

@Database(entities= arrayOf(Material::class), version=1)
abstract class MaterialDatabase : RoomDatabase() {
    abstract fun materialDao(): IMaterialDAO
}