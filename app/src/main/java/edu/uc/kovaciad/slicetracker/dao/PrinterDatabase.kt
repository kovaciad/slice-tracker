package edu.uc.kovaciad.slicetracker.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.uc.kovaciad.slicetracker.dto.Printer

@Database(entities = arrayOf(Printer::class), version=1)
abstract class PrinterDatabase : RoomDatabase() {
    abstract fun printerDao(): IPrinterDAO
}