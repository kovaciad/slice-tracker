package edu.uc.kovaciad.slicetracker.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Printer (@PrimaryKey val pid: Int,
                    @ColumnInfo(name = "name") var name: String,
                    @ColumnInfo(name = "type") var type: Int,
                    @ColumnInfo(name = "brand") var brandId: Int,
                    @ColumnInfo(name = "url") var url: String)
{
    override fun toString(): String {
        return "$name, $type, $brandId, $url"
    }
}