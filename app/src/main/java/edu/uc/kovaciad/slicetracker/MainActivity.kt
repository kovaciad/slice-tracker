package edu.uc.kovaciad.slicetracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import edu.uc.kovaciad.slicetracker.dao.SliceDatabase


class MainActivity : AppCompatActivity() {

    //Connection information to my database
    //Schema IT3048C

    val sqlInstance = SQLInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Update data in a coroutine for faster startup
        updateData(sqlInstance)
//        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }
//
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(Manifest.permission.INTERNET),
//            PackageManager.PERMISSION_GRANTED
//        )
//        textView = findViewById<View>(R.id.textView)
//
    }

    fun updateData(sqlInstance: SQLInstance) {
        val db = Room.databaseBuilder(
            applicationContext,
            SliceDatabase::class.java, "brand-db"
        ).build()
        DataUpdate.updateBrands(sqlInstance, db)
        DataUpdate.updatePrinters(sqlInstance, db)
        DataUpdate.updateModels(sqlInstance, db)
    }
}