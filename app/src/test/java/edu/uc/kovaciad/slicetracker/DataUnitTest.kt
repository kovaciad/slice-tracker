package edu.uc.kovaciad.slicetracker

import android.util.Log
import junit.framework.Assert.assertEquals
import org.junit.Test

class DataUnitTest {
    val sqlInstance = SQLInstance

    /**
     * Look for last name in the SQL Table
     * FIXME: Test is currently broken. Function needs context
     */
//    @Test
//    fun doesUpdateModelGetLastModel() {
//        val lastString = DataUpdate.updateModels(sqlInstance)
//        // Log.d("DEBUG", lastString)
//        assertEquals(lastString, "Thanksgiving Dragon Turkey")
//    }

    @Test
    fun twoplustwo() {
        assertEquals(4, 2+2)
    }
}