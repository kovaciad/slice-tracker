package edu.uc.kovaciad.slicetracker.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

open class SuperFragment : Fragment() {
    internal lateinit var viewModel: MainViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {
            viewModel = ViewModelProviders.of(it!!).get(MainViewModel::class.java)
        }
    }

    //TODO put shared Fragment Logic here
}