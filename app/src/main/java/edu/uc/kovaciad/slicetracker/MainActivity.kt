package edu.uc.kovaciad.slicetracker

import android.app.slice.Slice
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import edu.uc.kovaciad.slicetracker.dao.SliceDatabase
import edu.uc.kovaciad.slicetracker.dto.Brand
import edu.uc.kovaciad.slicetracker.ui.main.ApplicationViewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

    }


}
