package edu.uc.kovaciad.slicetracker.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

open class SuperFragment : Fragment() {
    protected lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        }
    }
}