package edu.uc.kovaciad.slicetracker.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param aid: DB ID for dev use
 * @param artistName: User Inputted Name
 * @param artistUrl: artist URL
 */
@Entity(tableName = "Artist")
class Artist(@PrimaryKey var aid: Int,
             @ColumnInfo(name = "artistName")var artistName: String,
             @ColumnInfo(name = "artistUrl")var artistUrl: String) {
        override fun toString(): String {
            return artistName
        }
}