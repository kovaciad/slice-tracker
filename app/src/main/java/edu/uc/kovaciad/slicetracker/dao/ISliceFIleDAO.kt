package edu.uc.kovaciad.slicetracker.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.uc.kovaciad.slicetracker.dto.SliceFile

@Dao
interface ISliceFIleDAO {
    @Query("SELECT * FROM SliceFile")
    fun getAllSliceFile(): LiveData<List<SliceFile>>

    @Query("SELECT * FROM SliceFile WHERE SliceFileName LIKE :name")
    fun findSliceFile(name: String): SliceFile

    @Insert
    fun insertSliceFile(sliceFile: SliceFile)

    @Delete
    fun deleteSliceFile(sliceFile: SliceFile)

}
