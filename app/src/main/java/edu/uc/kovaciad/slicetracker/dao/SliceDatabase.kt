package edu.uc.kovaciad.slicetracker.dao

import android.content.Context
import androidx.room.*
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


    private var INSTANCE: SliceDatabase? = null

    fun getDatabase(
        context: Context
    ): SliceDatabase? {
        if (INSTANCE == null) {
            synchronized(SliceDatabase::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        SliceDatabase::class.java, "SliceDatabase.db"
                    ) // Wipes and rebuilds instead of migrating
                        // if no Migration object.

                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }
        return INSTANCE
    }


}
