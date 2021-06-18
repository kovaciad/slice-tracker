package edu.uc.kovaciad.slicetracker.dto

data class Printer (var name: String, var type: String, var brand: Brand) {
    override fun toString(): String {
        return "$name, $type, $brand"
    }
    fun editPrinter(name: String = this.name, type: String = this.type, brand: Brand = this.brand) {
        this.name = name
        this.type = type
        this.brand = brand
    }
}