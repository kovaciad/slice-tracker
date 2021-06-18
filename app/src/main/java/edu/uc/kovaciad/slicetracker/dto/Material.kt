package edu.uc.kovaciad.slicetracker.dto

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param mid: DB ID for dev use
 * @param name: User Inputted Name
 * @param type: Type of material from predetermined list
 * @param brandId: Brand from brand object
 * @param color: Color of filament. Uses android color ints
 * @param amount: Amount of filament in g or L
 */
@Entity
data class Material (@PrimaryKey var mid: Int,
                     @ColumnInfo(name = "name") var name: String,
                     @ColumnInfo(name = "type") var type: Int,
                     @ColumnInfo(name = "brand") var brandId: Int,
                     @ColumnInfo(name = "color") var color: Int,
                     @ColumnInfo(name = "amount") var amount: Double) {
    override fun toString(): String {
        return "$name, $type, $brandId, $color, $amount"
    }
}