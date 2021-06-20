package edu.uc.kovaciad.slicetracker

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.uc.kovaciad.slicetracker.dao.IBrandDAO
import org.junit.Before
import androidx.room.Room
import edu.uc.kovaciad.slicetracker.dao.SliceDatabase
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
//    private lateinit var brandDao: IBrandDAO
//    private val context = ApplicationProvider.getApplicationContext<Context>()
//    private var db: SliceDatabase = Room.inMemoryDatabaseBuilder(
//        context, SliceDatabase::class.java
//    ).build()
//    private lateinit var ma: MainActivity
//
//    @Before
//    fun createDb() {
//        brandDao = db.brandDao()
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun updateAndReadDB() {
//        DataUpdate.updateBrands(db)
//        val brandList = brandDao.getAll()
//        assertEquals(brandList[0], "Elegoo")
//    }
}