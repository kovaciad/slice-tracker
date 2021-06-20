package edu.uc.kovaciad.slicetracker

import android.util.Log
import androidx.room.Room
import edu.uc.kovaciad.slicetracker.dao.SliceDatabase
import junit.framework.Assert.assertEquals
import org.junit.Test

class DataUnitTest {
    val sqlInstance = SQLInstance

    val db = Room.databaseBuilder(
        context,
        SliceDatabase::class.java, "brand-db"
    ).build()

    /**
     * Look for last name in the SQL Table
     * FIXME: Test is currently broken. Function needs context
     */
    @Test
    fun doesUpdateModelGetLastModel() {
        val lastString = DataUpdate.updateModels(sqlInstance, db)
        // Log.d("DEBUG", lastString)
        assertEquals(lastString, "Thanksgiving Dragon Turkey")
    }

    @Test
    fun twoplustwo() {
        assertEquals(4, 2+2)
    }
}