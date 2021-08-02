package edu.uc.kovaciad.slicetracker.ui.main

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.uc.kovaciad.slicetracker.R
import edu.uc.kovaciad.slicetracker.dto.Printer


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Hardcode the values of matTypeSpinner (see in strings.xml)
        val matTypeSpinner = getView()?.findViewById<Spinner>(R.id.matTypeSpinner)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.matTypeArray,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            if (matTypeSpinner != null) {
                matTypeSpinner.adapter = it
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }


}