package edu.uc.kovaciad.slicetracker.dto

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param mid: DB ID for dev use
 * @param materialName: User Inputted Name
 * @param materialTypeId: Type of material from predetermined list
 * @param brandId: Brand from brand object
 * @param materialColor: Color of filament. Uses android color ints
 * @param materialAmount: Amount of filament in g or L
 */
@Entity(tableName = "material")
data class Material(@PrimaryKey var mid: Int,
                    @ColumnInfo(name = "materialName") var materialName: String,
                    @ColumnInfo(name = "materialTypeId") var materialTypeId: Int,
                    @ColumnInfo(name = "brandId") var brandId: Int,
                    @ColumnInfo(name = "materialColor") var materialColor: Int,
                    @ColumnInfo(name = "materialAmount") var materialAmount: Double) {
    override fun toString(): String {
        return materialName
    }
}