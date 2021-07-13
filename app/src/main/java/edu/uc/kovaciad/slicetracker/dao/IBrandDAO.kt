package edu.uc.kovaciad.slicetracker.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.uc.kovaciad.slicetracker.dto.Brand

@Dao
interface IBrandDAO {
    @Query("SELECT * FROM Brand")
    fun getAllBrands(): LiveData<List<Brand>>

    @Query("SELECT * FROM Brand WHERE brandName LIKE :name")
    fun findBrandByName(name: String): Brand

    @Insert
    fun insertBrand(brand: Brand)

    @Delete
    fun deleteBrand(brand: Brand)

}