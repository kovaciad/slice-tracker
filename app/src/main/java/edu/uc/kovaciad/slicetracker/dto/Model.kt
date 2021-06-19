package edu.uc.kovaciad.slicetracker.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Model (@PrimaryKey var mid: Int,
                  @ColumnInfo(name = "name")var name: String,
                  @ColumnInfo(name = "url")var url: String) {
    override fun toString(): String {
        return "$name, $url"
    }
}