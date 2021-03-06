package edu.uc.kovaciad.slicetracker.dto

/**
 * @param id: DB ID for dev use
 * @param modelName: User Inputted Name
 * @param artistId: Artist ID from Artist table
 * @param url: model URL
 */
data class Model (
    var modelName: String = "",
    var artistId: String = "",
    var url: String? = null,
    override var id: String = ""): IData {

    override fun toString(): String {
        return modelName
    }
}