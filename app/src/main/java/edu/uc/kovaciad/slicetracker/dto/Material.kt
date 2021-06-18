package edu.uc.kovaciad.slicetracker.dto

import android.graphics.Color

/**
 * @param name: User Inputted Name
 * @param type: Type of material from predetermined list
 * @param brand: Brand from brand object
 * @param color: Color of filament. Uses android color ints
 * @param amount: Amount of filament in g
 */
data class Material (var name: String, var type: String, var brand: Brand, var color: Int, var amount: Double) {
    override fun toString(): String {
        return "$name, $type, $brand, $color, $amount"
    }
}