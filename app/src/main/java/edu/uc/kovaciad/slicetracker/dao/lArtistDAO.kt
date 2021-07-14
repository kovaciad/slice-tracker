package edu.uc.kovaciad.slicetracker.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.uc.kovaciad.slicetracker.dto.Artist

@Dao
interface lArtistDAO {
    @Query("SELECT * FROM Artist")
    fun getAllSliceFile(): LiveData<List<Artist>>

    @Query("SELECT * FROM Artist WHERE ArtistName LIKE :name")
    fun findSliceFile(name: String): Artist

    @Insert
    fun insertSliceFile(sliceFile: Artist)

    @Delete
    fun deleteSliceFile(sliceFile: Artist)

}
