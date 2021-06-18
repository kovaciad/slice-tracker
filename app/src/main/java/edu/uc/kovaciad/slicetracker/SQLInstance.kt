package edu.uc.kovaciad.slicetracker

import java.sql.Connection
import java.sql.DriverManager
import android.util.Log

class SQLInstance {
    private val ip = "173.248.174.34"
    private val port = "1533"
    private val Classes = "net.sourceforge.jtds.jdbc.Driver"
    private val database = "personaldatabase"
    private val username = "webapplication"
    private val password = "I@mtheW@lrus1"
    private val url = "jdbc:jtds:sqlserver://$ip:$port/$database"

    private var _connection : Connection? = null
    var connection: Connection? = null
        get() {
            return _connection

        }

    var sqlThread = Thread( {
        run() {
            try {
                Class.forName(Classes)
                _connection = DriverManager.getConnection(url, username, password)
                Log.d("SQL", "Connection Success")
            } catch (e: Exception) {
                Log.d("SQL", e.toString())
            }
        }
    })
}