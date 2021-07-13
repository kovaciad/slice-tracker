package edu.uc.kovaciad.slicetracker.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.uc.kovaciad.slicetracker.dto.Material
import edu.uc.kovaciad.slicetracker.dto.Printer

@Dao
interface IMaterialDAO {
    @Query("SELECT * FROM material")
    fun getAll(): LiveData<List<Material>>

    @Query("SELECT * FROM material WHERE materialName LIKE :name")
    fun findByName(name: String): Material

    @Insert
    suspend fun insertMaterial(material: Material)
    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param  material new value to write
     */
    @Update
    suspend fun update(material: Material)
    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM material")
    suspend fun clear()

}