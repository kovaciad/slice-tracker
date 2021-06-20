package edu.uc.kovaciad.slicetracker

import android.R
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import edu.uc.kovaciad.slicetracker.ui.main.MainFragment
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
//        val policy = ThreadPolicy.Builder().permitAll().build()
//        StrictMode.setThreadPolicy(policy)
//        try {
//            Class.forName(Classes)
//            connection = DriverManager.getConnection(url, username, password)
//            textView.setText("SUCCESS")
//        } catch (e: ClassNotFoundException) {
//            e.printStackTrace()
//            textView.setText("ERROR")
//        } catch (e: SQLException) {
//            e.printStackTrace()
//            textView.setText("FAILURE")
//        }
    }


}