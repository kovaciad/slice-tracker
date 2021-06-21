package edu.uc.kovaciad.slicetracker.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.uc.kovaciad.slicetracker.dto.Model
import edu.uc.kovaciad.slicetracker.dto.Printer

@Dao
interface IModelDAO {
    @Query("SELECT * FROM Model")
    fun getAll(): LiveData<List<Model>>

    @Query("SELECT * FROM Model WHERE modelName LIKE :name")
    fun findByName(name: String): Model

    @Insert
    fun insertModel(model: Model)

    @Delete
    fun deleteModel(model: Model)

}