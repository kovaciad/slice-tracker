package edu.uc.kovaciad.slicetracker.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.uc.kovaciad.slicetracker.dto.Model

@Database(entities = arrayOf(Model::class), version=1)
abstract class ModelDatabase: RoomDatabase() {
    abstract fun modelDao(): IModelDAO
}