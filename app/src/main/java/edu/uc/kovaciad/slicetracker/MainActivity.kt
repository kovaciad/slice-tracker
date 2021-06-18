package edu.uc.kovaciad.slicetracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import edu.uc.kovaciad.slicetracker.dao.BrandDatabase
import edu.uc.kovaciad.slicetracker.dto.Brand


class MainActivity : AppCompatActivity() {

    //Connection information to my database
    //Schema IT3048C

    val sqlInstance = SQLInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateBrands(sqlInstance)
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

    fun updateBrands(sqlInstance: SQLInstance) {
        val db = Room.databaseBuilder(
            applicationContext,
            BrandDatabase::class.java, "brand-db"
        ).build()
        val statement = sqlInstance.connection!!.createStatement()
        val brandRS = statement?.executeQuery("SELECT * FROM IT3048C.PrintingBrand")
        do {
            val bid: Int = brandRS!!.getInt(1)
            val name: String = brandRS!!.getString(2)
            val url: String = brandRS!!.getString(3)
            val brand = Brand(bid, name, url)

            val brandDao = db.brandDao()
            brandDao.insertBrand(brand)
        } while (brandRS!!.next())

    }


}