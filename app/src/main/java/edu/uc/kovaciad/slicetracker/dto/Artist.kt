package edu.uc.kovaciad.slicetracker.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param id: DB ID for dev use
 * @param artistName: User Inputted Name
 * @param artistUrl: artist URL
 */
data class Artist (
            var artistName: String,
            var artistUrl: String,
            override var id: String = ""): IData {

    override fun toString(): String {
        return artistName
    }
}