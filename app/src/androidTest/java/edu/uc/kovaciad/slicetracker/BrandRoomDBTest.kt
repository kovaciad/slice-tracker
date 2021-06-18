package edu.uc.kovaciad.slicetracker

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.uc.kovaciad.slicetracker.dao.IBrandDAO
import org.junit.Before
import java.io.IOException
import androidx.room.Room
import edu.uc.kovaciad.slicetracker.dao.BrandDatabase
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var brandDao: IBrandDAO
    private lateinit var db: BrandDatabase
    private lateinit var ma: MainActivity

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, BrandDatabase::class.java
        ).build()
        brandDao = db.brandDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun updateAndReadDB() {
        ma.updateBrands(ma.sqlInstance)
        val brandList = brandDao.getAll()
        assertEquals(brandList[0], "Elegoo")
    }
}