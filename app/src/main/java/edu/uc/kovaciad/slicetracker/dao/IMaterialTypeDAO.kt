package edu.uc.kovaciad.slicetracker.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.uc.kovaciad.slicetracker.dto.MaterialType

@Dao
interface IMaterialTypeDAO {
    @Query("SELECT * FROM MaterialType")
    fun getAllMaterialTypes(): LiveData<List<MaterialType>>

    @Query("SELECT * FROM MaterialType WHERE MaterialTypeName LIKE :name")
    fun findMaterialTypeByName(name: String): MaterialType

    @Insert
    fun insertMaterialType(materialType: MaterialType)

    @Delete
    fun deleteMaterialType(materialType: MaterialType)

}