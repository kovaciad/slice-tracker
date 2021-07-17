package edu.uc.kovaciad.slicetracker.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param id: DB ID for dev use
 * @param brandName: User Inputted Name
 */
data class Brand(
            var brandName: String = "",
            var brandURL: String? = null,
            override var id: String = ""): IData {
}