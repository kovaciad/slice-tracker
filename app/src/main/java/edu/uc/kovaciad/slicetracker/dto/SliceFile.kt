package edu.uc.kovaciad.slicetracker.dto

import android.text.format.Time
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
/**
 * @param sfid: DB ID for dev use
 * @param sliceFileName: User Inputted Name
 * @param modelId: model id
 * @param materialId: material id
 * @param resinBaseLayers: Total Base layers for resin
 * @param resinBaseLayerCureTime: Cure time per base layer
 * @param resinLayerThickness: Layer thickness for resin
 * @param resinLayerCureTime: Layer cure time for resin (not base layer)
 * @param resinLiftSpeed: Speed that the printer raises at (mm/s)
 * @param resinRetractSpeed: Speed that the printer retracts at (mm/s)
 * @param resinLiftHeight: Height that printer stops at after raising (mm)
 * @param numberOfLayers: Number of layers - this is for both resin and filament
 * @param filamentNozzleThickness: Thickness of nozzle printing filament
 * @param filamentNozzleEstimatedTime: Estimated amount of print time, filament
 * @param filamentEstimatedMaterial: estimated amount of filament needed
 */
@Entity(tableName = "SliceFile")
data class SliceFile (@PrimaryKey var sfid: Int,
                      @ColumnInfo(name = "sliceFileName") var sliceFileName : String,
                      @ColumnInfo(name = "modelId") var modelId : Int,
                      @ColumnInfo(name = "materialId") var materialId : Int,
                      @ColumnInfo(name = "resinBaseLayers") var resinBaseLayers : Int = 0,
                      @ColumnInfo(name = "resinBaseLayerCureTime") var resinBaseLayerCureTime : Int = 0,
                      @ColumnInfo(name = "resinLayerThickness") var resinLayerThickness : Double = 0.0,
                      @ColumnInfo(name = "resinLayerCureTime") var resinLayerCureTime : Double = 0.0,
                      @ColumnInfo(name = "resinLiftSpeed") var resinLiftSpeed : Int = 0,
                      @ColumnInfo(name = "resinRetractSpeed") var resinRetractSpeed : Int = 0,
                      @ColumnInfo(name = "resinLiftHeight") var resinLiftHeight : Int = 0,
                      @ColumnInfo(name = "numberOfLayers") var numberOfLayers : Int = 0,
                      @ColumnInfo(name = "filamentNozzleThickness") var filamentNozzleThickness : Double = 0.0,
                      @ColumnInfo(name = "filamentNozzleEstimatedTime") var filamentNozzleEstimatedTime : Time,
                      @ColumnInfo(name = "filamentEstimatedMaterial") var filamentEstimatedMaterial : Double = 0.0) {
    override fun toString(): String {
        return sliceFileName
    }
}
