package edu.uc.kovaciad.slicetracker.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param bid: DB ID for dev use
 * @param brandName: User Inputted Name
 * @param brandUrl: Brand URL
 */
@Entity(tableName = "Brand")
data class Brand(@PrimaryKey val bid: Int,
                @ColumnInfo(name = "brandName") val brandName: String,
                @ColumnInfo(name = "brandUrl") val url: String) {
    override fun toString(): String {
        return brandName
    }
}