package edu.uc.kovaciad.slicetracker.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.uc.kovaciad.slicetracker.dto.Printer

@Dao
interface IPrinterDAO {
    @Query("SELECT * FROM printers")
    fun getAll(): ArrayList<Printer>

    @Query("SELECT * FROM printers WHERE name LIKE :name")
    fun findByName(name: String): Printer

    @Insert
    fun insertPrinter(printer: Printer)

    @Delete
    fun deletePrinter(printer: Printer)

}