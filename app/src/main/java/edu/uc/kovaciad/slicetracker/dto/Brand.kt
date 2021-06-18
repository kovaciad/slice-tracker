package edu.uc.kovaciad.slicetracker.dto

data class Brand(var name: String, var logo: Int) {
    override fun toString(): String {
        return name
    }
}