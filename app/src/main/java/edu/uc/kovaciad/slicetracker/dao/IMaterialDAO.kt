package edu.uc.kovaciad.slicetracker.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.uc.kovaciad.slicetracker.dto.Material
import edu.uc.kovaciad.slicetracker.dto.Printer

@Dao
interface IMaterialDAO {
    @Query("SELECT * FROM material")
    fun getAll(): LiveData<List<Material>>

    @Query("SELECT * FROM material WHERE name LIKE :name")
    fun findByName(name: String): Material

    @Insert
    fun insertMaterial(material: Material)

    @Delete
    fun deleteMaterial(material: Material)

}