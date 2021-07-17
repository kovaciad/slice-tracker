package edu.uc.kovaciad.slicetracker.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import edu.uc.kovaciad.slicetracker.R


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

}