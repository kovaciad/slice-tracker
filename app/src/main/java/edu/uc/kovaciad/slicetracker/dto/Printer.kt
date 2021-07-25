package edu.uc.kovaciad.slicetracker.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * @param pid: DB ID for dev use
 * @param printerName: User Inputted Name
 * @param brandId: Brand ID
 * @param materialTypeId: material type id
 * @param modelUrl: model URL
 */
@Entity(tableName = "Printer")
data class Printer(@PrimaryKey val pid: Int,
                   @ColumnInfo(name = "printerName") var printerName: String,
                   @ColumnInfo(name = "materialTypeId") var materialTypeId: Int,
                   @ColumnInfo(name = "brandId") var brandId: Int = 0,
                   @ColumnInfo(name = "printerUrl") var printerUrl: String) {
    override fun toString(): String {
        return printerName
    }
}