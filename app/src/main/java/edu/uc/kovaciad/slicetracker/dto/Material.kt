package edu.uc.kovaciad.slicetracker.dto

/**
 * @param id: DB ID for dev use
 * @param materialName: User Inputted Name
 * @param materialTypeId: Type of material from predetermined list
 * @param brandId: Brand from brand object
 * @param materialColor: Color of filament. Uses android color ints
 * @param materialAmount: Amount of filament in g or L
 */
data class Material (
            var materialName: String = "",
            var materialTypeId: String = "",
            var brandId: String = "",
            var materialColor: String = "",
            var materialAmount: Double= 0.0,
            override var id: String = ""): IData {

    override fun toString(): String {
        return materialName
    }
}