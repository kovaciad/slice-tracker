package edu.uc.kovaciad.slicetracker.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

///**
// * @param bid: DB ID for dev use
// * @param brandName: User Inputted Name
// */
//@Entity(tableName = "Brand")
//data class Brand(@PrimaryKey val bid: Int,
//                @ColumnInfo(name = "brandName") val brandName: String,
//                @ColumnInfo(name = "brandUrl") val brandURL: String) {
//    override fun toString(): String {
//        return brandName
//    }
//}

@Entity
class Brand {
    @PrimaryKey()
    var bid: Int = 0


    @ColumnInfo(name = "brandName")
    var brandName: String = ""

    @ColumnInfo(name = "brandURL")
    var brandURL: String? = null
}