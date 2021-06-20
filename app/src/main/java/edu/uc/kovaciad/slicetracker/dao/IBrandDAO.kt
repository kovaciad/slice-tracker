package edu.uc.kovaciad.slicetracker.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.uc.kovaciad.slicetracker.dto.Brand

@Dao
interface IBrandDAO {
    @Query("SELECT * FROM brand")
    fun getAll(): List<Brand>

    @Query("SELECT * FROM brand WHERE name LIKE :name")
    fun findByName(name: String): Brand

    @Insert
    fun insertBrand(brand: Brand)

    @Delete
    fun deleteBrand(brand: Brand)

}