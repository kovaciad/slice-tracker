package edu.uc.kovaciad.slicetracker

import edu.uc.kovaciad.slicetracker.dto.Brand
import edu.uc.kovaciad.slicetracker.dto.Material
import edu.uc.kovaciad.slicetracker.dto.Printer
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests used to test Brand, Printer, and Print data types
 */
class DataUnitTest {

    @Test
    fun BrandCreation() {
        var crealityBrand: Brand = createBrand()
        assertEquals(crealityBrand.toString(), "Creality")
    }

    @Test
    fun PrinterCreation() {
        var crealityBrand: Brand = createBrand()
        var printer = Printer("Ender 3 Pro", "FDM", crealityBrand)
        assertEquals(printer.toString(), "Ender 3 Pro, FDM, Creality")
        assertEquals(printer.brand.toString(), "Creality")
    }

    @Test
    fun EditPrinterTest() {
        var crealityBrand: Brand = createBrand()
        var printer = Printer("Ender 3 Pro", "FDM", crealityBrand)
        printer.editPrinter(type = "Resin")
        assertEquals(printer.toString(), "Ender 3 Pro, Resin, Creality")
        assertEquals(printer.brand.toString(), "Creality")
    }

    @Test
    fun MaterialCreation() {
        var crealityBrand: Brand = createBrand()
        var material = Material("Blue Resin", "PLA+", crealityBrand,
                                    0xF0F8FF, 1000.000)
        assertEquals(material.toString(), "Blue Resin, PLA+, Creality, 15792383, 1000.0")
    }

    fun createBrand(): Brand {
        return Brand("Creality", android.R.drawable.btn_star_big_on)
    }

}