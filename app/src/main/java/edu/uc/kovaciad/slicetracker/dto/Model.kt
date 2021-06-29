package edu.uc.kovaciad.slicetracker.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param mId: DB ID for dev use
 * @param modelName: User Inputted Name
 * @param artistId: Artist ID from Artist table
 * @param modelUrl: model URL
 */
@Entity(tableName = "Model")
data class Model (@PrimaryKey var mId: Int,
                  @ColumnInfo(name = "modelName")var modelName: String,
                  @ColumnInfo(name = "artistId")var artistId: Int,
                  @ColumnInfo(name = "modelUrl")var url: String)
{
    override fun toString(): String {
        return modelName
    }
}