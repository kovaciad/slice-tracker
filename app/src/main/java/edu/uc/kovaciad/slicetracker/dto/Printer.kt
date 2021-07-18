package edu.uc.kovaciad.slicetracker.dto


/**
 * @param id: DB ID for dev use
 * @param printerName: User Inputted Name
 * @param brandId: Brand ID
 * @param materialTypeId: material type id
 * @param printerUrl: model URL
 */
data class Printer (
            var printerName: String = "",
            var materialTypeId: String = "",
            var brandId: String = "",
            var printerUrl: String? = null,
            override var id: String = ""): IData
{
    override fun toString(): String {
        return printerName
    }
}