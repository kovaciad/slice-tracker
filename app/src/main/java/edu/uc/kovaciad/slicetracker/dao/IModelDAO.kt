package edu.uc.kovaciad.slicetracker.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.uc.kovaciad.slicetracker.dto.Model
import edu.uc.kovaciad.slicetracker.dto.Printer

interface IModelDAO {
    @Query("SELECT * FROM Model")
    fun getAll(): List<Printer>

    @Query("SELECT * FROM Model WHERE name LIKE :name")
    fun findByName(name: String): Printer

    @Insert
    fun insertModel(model: Model)

    @Delete
    fun deleteModel(model: Model)
}