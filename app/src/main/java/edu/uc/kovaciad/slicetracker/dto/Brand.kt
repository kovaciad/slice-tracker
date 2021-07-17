package edu.uc.kovaciad.slicetracker.dto

/**
 * @param id: DB ID for dev use
 * @param brandName: User Inputted Name
 */
data class Brand(
            var brandName: String = "",
            var brandURL: String? = null,
            override var id: String = ""): IData {
}