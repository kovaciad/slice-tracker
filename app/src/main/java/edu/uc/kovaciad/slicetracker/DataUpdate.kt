package edu.uc.kovaciad.slicetracker

import android.content.Context
import android.util.Log
import androidx.room.Room
import edu.uc.kovaciad.slicetracker.dao.BrandDatabase
import edu.uc.kovaciad.slicetracker.dao.ModelDatabase
import edu.uc.kovaciad.slicetracker.dao.PrinterDatabase
import edu.uc.kovaciad.slicetracker.dto.Brand
import edu.uc.kovaciad.slicetracker.dto.Model
import edu.uc.kovaciad.slicetracker.dto.Printer
import java.sql.SQLException

object DataUpdate {
    fun updateBrands(sqlInstance: SQLInstance, context: Context) {
        val db = Room.databaseBuilder(
            context,
            BrandDatabase::class.java, "brand-db"
        ).build()
        try {
            val statement = sqlInstance.connection!!.createStatement()
            val brandRS = statement?.executeQuery("SELECT * FROM IT3048C.PrintingBrand")
            do {
                val bid: Int = brandRS!!.getInt(1)
                val name: String = brandRS.getString(2)
                val url: String = brandRS.getString(3)
                val brand = Brand(bid, name, url)

                val brandDao = db.brandDao()
                brandDao.insertBrand(brand)
            } while (brandRS!!.next())
        } catch (e: SQLException) {
            Log.d("SQL", "SQL Error")
        }
    }

    fun updatePrinters(sqlInstance: SQLInstance, context: Context) {
        val db = Room.databaseBuilder(
            context,
            PrinterDatabase::class.java, "printer-db"
        ).build()
        val statement = sqlInstance.connection!!.createStatement()
        val printerRS = statement?.executeQuery("SELECT * FROM IT3048C.Printer")
        do {
            val pid: Int = printerRS!!.getInt(1)
            val name: String = printerRS.getString(2)
            val brand: Int = printerRS.getInt(4)
            val url: String = printerRS.getString(3)
            val type: Int = printerRS.getInt(5)

            val printer = Printer(pid, name, type, brand, url)

            val printerDao = db.printerDao()
            printerDao.insertPrinter(printer)
        } while (printerRS!!.next())
    }

    fun updateModels(sqlInstance: SQLInstance, context: Context): String {
        lateinit var unitTestResult: String
        val db = Room.databaseBuilder(
            context,
            ModelDatabase::class.java, "model-db"
        ).build()
        val statement = sqlInstance.connection!!.createStatement()
        val modelRS = statement?.executeQuery("SELECT * FROM IT3048C.Model")
        do {
            val mid: Int = modelRS!!.getInt(1)
            val name: String = modelRS.getString(2)
            val url: String = modelRS.getString(3)
            // val artist: Int = modelRS.getInt(4)
            unitTestResult = name

            val model = Model(mid, name, url)

            val modelDao = db.modelDao()
            modelDao.insertModel(model)
        } while (modelRS!!.next())
        return unitTestResult
    }
}