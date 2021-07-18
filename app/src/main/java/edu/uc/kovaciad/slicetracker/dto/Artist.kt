package edu.uc.kovaciad.slicetracker.dto

/**
 * @param id: DB ID for dev use
 * @param artistName: User Inputted Name
 * @param artistUrl: artist URL
 */
data class Artist (
            var artistName: String = "",
            var artistUrl: String? = null,
            override var id: String = ""): IData {

    override fun toString(): String {
        return artistName
    }
}