package edu.uc.kovaciad.slicetracker

import java.sql.Connection
import java.sql.DriverManager
import android.util.Log
import java.sql.SQLException

// TODO: Replace this with retrofit. This won't work
object SQLInstance {

    private lateinit var _connection: Connection

    // Protect _connection from ever being changed by not having a setter
//    var connection: Connection = _connection
//        get() {
//            return _connection
//        }

//    fun createSQLConnection() {
//        try {
//            Class.forName(Classes).newInstance()
//            _connection = DriverManager.getConnection(url, username, password)
//            Log.d("SQL", "Connection Success")
//        } catch (e: SQLException) {
//            Log.d("SQL", "SQL Failure")
//            e.printStackTrace()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }



}