package edu.uc.kovaciad.slicetracker.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.uc.kovaciad.slicetracker.dto.Material
import edu.uc.kovaciad.slicetracker.dto.Printer

interface IMaterialDAO {
    @Query("SELECT * FROM material")
    fun getAll(): List<Printer>

    @Query("SELECT * FROM material WHERE name LIKE :name")
    fun findByName(name: String): Material

    @Insert
    fun insertMaterial(material: Material)

    @Delete
    fun deleteMaterial(material: Material)
}