package edu.uc.kovaciad.slicetracker.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.uc.kovaciad.slicetracker.dto.Printer

interface IPrinterDAO {
    @Query("SELECT * FROM printer")
    fun getAll(): List<Printer>

    @Query("SELECT * FROM printer WHERE name LIKE :name")
    fun findByName(name: String): Printer

    @Insert
    fun insertMaterial(printer: Printer)

    @Delete
    fun deleteMaterial(printer: Printer)
}