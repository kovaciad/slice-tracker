package edu.uc.kovaciad.slicetracker.dto

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param id: DB ID for dev use
 * @param materialName: User Inputted Name
 * @param materialTypeId: Type of material from predetermined list
 * @param brandId: Brand from brand object
 * @param materialColor: Color of filament. Uses android color ints
 * @param materialAmount: Amount of filament in g or L
 */
data class Material (
            var materialName: String,
            var materialTypeId: Int,
            var brandId: Int,
            var materialColor: Int,
            var materialAmount: Double,
            override var id: String = ""): IData {

    override fun toString(): String {
        return materialName
    }
}