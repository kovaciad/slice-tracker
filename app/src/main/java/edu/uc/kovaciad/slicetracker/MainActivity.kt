package edu.uc.kovaciad.slicetracker

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import edu.uc.kovaciad.slicetracker.services.BrandService


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var db = BrandService(application)
        var queryString : String = "INSERT INTO Brand (bid, brandName, brandURL) VALUES (1, 'Anycubic', 'www.anycubic.com')"
        val query = SimpleSQLiteQuery(queryString)
        Thread {
            db.getBrandDAO().query(query)
        }.start()
        //db.execSQL("INSERT INTO Brand (bid, brandName, brandURL) VALUES (2, 'Elegoo', 'www.elegpp.com')")


        setContentView(R.layout.main_activity)

    }


}
