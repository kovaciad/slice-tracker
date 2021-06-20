package edu.uc.kovaciad.slicetracker.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.uc.kovaciad.slicetracker.dto.Brand
import edu.uc.kovaciad.slicetracker.dto.Material
import edu.uc.kovaciad.slicetracker.dto.Model
import edu.uc.kovaciad.slicetracker.dto.Printer

@Database(entities = arrayOf(Brand::class, Material::class, Model::class, Printer::class), version=1)
abstract class SliceDatabase : RoomDatabase() {
    abstract fun printerDao(): IPrinterDAO
//    abstract fun materialDao(): IMaterialDAO
    abstract fun modelDao(): IModelDAO
    abstract fun brandDao(): IBrandDAO
}