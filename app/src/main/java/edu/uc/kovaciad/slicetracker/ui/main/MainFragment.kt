package edu.uc.kovaciad.slicetracker.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import edu.uc.kovaciad.slicetracker.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // To UI Designer: This is where you'll find your AutoComplete Data. Just fill it in
        viewModel.brands.observe(viewLifecycleOwner, Observer {
                brands ->
        })
        viewModel.fetchBrands()
        viewModel.printers.observe(viewLifecycleOwner, Observer {
                printers ->
        })
        viewModel.fetchPrinters()
        viewModel.models.observe(viewLifecycleOwner, Observer {
                models ->
        })
        viewModel.fetchModels()
    }

}