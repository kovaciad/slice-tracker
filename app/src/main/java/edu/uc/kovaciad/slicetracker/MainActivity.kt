package edu.uc.kovaciad.slicetracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.sqlite.db.SimpleSQLiteQuery
import edu.uc.kovaciad.slicetracker.services.DatabaseService


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var db = DatabaseService(application)
        var queryString : String = "INSERT INTO Brand (bid, brandName, brandURL) VALUES (1, 'Anycubic', 'www.anycubic.com')"
        val query = SimpleSQLiteQuery(queryString)
        Thread {
            db.getRoomDB().query(query)
        }.start()

        setContentView(R.layout.main_activity)

    }


}
