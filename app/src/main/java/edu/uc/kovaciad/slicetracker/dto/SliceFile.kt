package edu.uc.kovaciad.slicetracker.dto


/**
 * @param id: DB ID for dev use
 * @param sliceFileName: User Inputted Name
 * @param model: model name
 * @param artist: artist name
 * @param printer: printer name
 * @param material: material used
 * @param resinBaseLayers: Total Base layers for resin
 * @param resinBaseLayerCureTime: Cure time per base layer
 * @param resinLayerThickness: Layer thickness for resin
 * @param resinLayerCureTime: Layer cure time for resin (not base layer)
 * @param resinLiftSpeed: Speed that the printer raises at (mm/s)
 * @param resinRetractSpeed: Speed that the printer retracts at (mm/s)
 * @param resinLiftHeight: Height that printer stops at after raising (mm)
 * @param numberOfLayers: Number of layers - this is for both resin and filament
 * @param filamentNozzleThickness: Thickness of nozzle printing filament
 * @param estimatedTime: Estimated amount of print time
 * @param estimatedMaterial: estimated amount of material needed
 */
data class SliceFile (
    var sliceFileName : String = "",
    var model : String = "",
    var artist : String = "",
    var printer : String = "",
    var material : String = "",
    var resinBaseLayers : Int = 0,
    var resinBaseLayerCureTime : Int = 0,
    var resinLayerThickness : Double = 0.0,
    var resinLayerCureTime : Double = 0.0,
    var resinLiftSpeed : Int = 0,
    var resinRetractSpeed : Int = 0,
    var resinLiftHeight : Int = 0,
    var numberOfLayers : Int = 0,
    var filamentNozzleThickness : Double = 0.0,
    var estimatedTime : Double = 0.0,
    var estimatedMaterial : Double = 0.0,
    override var id: String = ""): IData
{
    override fun toString(): String {
        return "$sliceFileName | Time: $estimatedTime min | Material: $estimatedMaterial g or L"
    }
}
