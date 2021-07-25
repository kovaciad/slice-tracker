package edu.uc.kovaciad.slicetracker.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param mtid: DB ID for dev use
 * @param materialTypeName: User Inputted Name
 */
@Entity(tableName = "MaterialType")
data class MaterialType(@PrimaryKey val mtid : Int,
                        @ColumnInfo(name = "materialTypeName") var materialTypeName : String) {
    override fun toString(): String {
        return materialTypeName
    }
}