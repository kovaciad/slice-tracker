package edu.uc.kovaciad.slicetracker.dto

/**
 * @param id: DB ID for dev use
 * @param materialTypeName: User Inputted Name
 */
data class MaterialType(
            var materialTypeName : String = "",
            override var id: String = ""): IData
{
    override fun toString(): String {
        return materialTypeName
    }
}