package edu.uc.kovaciad.slicetracker

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.uc.kovaciad.slicetracker.dao.IModelDAO
import edu.uc.kovaciad.slicetracker.dao.SliceDatabase
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.sql.SQLException


@RunWith(AndroidJUnit4::class)
class SQLInitTest {
//
//    private lateinit var db: SliceDatabase
//    @Before
//    fun createDb() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.databaseBuilder(
//            context,
//            SliceDatabase::class.java, "main-db"
//        ).build()
//    }
//
////    @After
////    @Throws(IOException::class)
////    fun closeDb() {
////        db.close()
////    }
//
//    @Test
//    @Throws(SQLException::class)
//    fun doesUpdateModelGetLastModel() {
//        DataUpdate.updateModels(db)
//        val modelDao: IModelDAO = db.modelDao()
//        val turkey = modelDao.findByName("Thanksgiving Dragon Turkey")
//        assertEquals(turkey.toString(), "Thanksgiving Dragon Turkey, https://thingiverse.com")
//    }

}