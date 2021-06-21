package edu.uc.kovaciad.slicetracker.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.uc.kovaciad.slicetracker.dto.Printer

@Dao
interface IPrinterDAO {
    @Query("SELECT * FROM Printer")
    fun getAll(): LiveData<List<Printer>>

    @Query("SELECT * FROM Printer WHERE printerName LIKE :name")
    fun findByName(name: String): Printer

    @Insert
    fun insertPrinter(printer: Printer)

    @Delete
    fun deletePrinter(printer: Printer)

}