package edu.uc.kovaciad.slicetracker.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.uc.kovaciad.slicetracker.dto.Brand
import edu.uc.kovaciad.slicetracker.dto.Material

@Dao
interface IBrandDAO {
    @Query("SELECT * FROM BRAND")
    fun getAll(): LiveData<List<Brand>>

    @Query("SELECT * FROM Brand WHERE brandName LIKE :name")
    fun findByName(name: String): Brand

    @Insert
    fun insertBrand(brand: Brand)
    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param brand new value to write
     */
    @Update
    suspend fun update(brand: Brand)

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM Brand")
    suspend fun clear()

}