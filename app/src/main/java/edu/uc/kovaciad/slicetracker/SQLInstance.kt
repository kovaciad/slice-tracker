package edu.uc.kovaciad.slicetracker

import java.sql.Connection
import java.sql.DriverManager
import android.util.Log
import java.sql.SQLException

// TODO: Replace this with retrofit. This won't work
object SQLInstance {
    private val ip = "173.248.174.34"
    private val port = "1533"
    private val Classes = "net.sourceforge.jtds.jdbc.Driver"
    private val database = "personaldatabase"
    private val username = "webapplication"
    private val password = "I@mtheW@lrus1"
    private val url = "jdbc:jtds:sqlserver://$ip:$port/$database"
    private lateinit var _connection: Connection

    // Protect _connection from ever being changed by not having a setter
    var connection: Connection = _connection
        get() {
            return _connection
        }

    fun createSQLConnection() {
        try {
            Class.forName(Classes).newInstance()
            _connection = DriverManager.getConnection(url, username, password)
            Log.d("SQL", "Connection Success")
        } catch (e: SQLException) {
            Log.d("SQL", "SQL Failure")
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



}