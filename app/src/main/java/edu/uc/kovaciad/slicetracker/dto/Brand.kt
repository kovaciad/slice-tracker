package edu.uc.kovaciad.slicetracker.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Brand(@PrimaryKey val bid: Int,
                @ColumnInfo(name = "name") val name: String,
                @ColumnInfo(name = "url") val url: String) {
    override fun toString(): String {
        return name
    }
}