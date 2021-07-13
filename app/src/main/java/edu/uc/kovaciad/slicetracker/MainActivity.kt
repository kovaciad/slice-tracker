package edu.uc.kovaciad.slicetracker

import android.app.slice.Slice
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import edu.uc.kovaciad.slicetracker.dao.SliceDatabase
import edu.uc.kovaciad.slicetracker.dto.Brand

import kotlinx.android.synthetic.main.activity_main.*

/**
 * This main activity is just a container for our fragments,
 * where the real action is.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
