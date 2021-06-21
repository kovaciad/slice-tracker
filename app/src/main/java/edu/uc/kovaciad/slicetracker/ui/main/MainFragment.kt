package edu.uc.kovaciad.slicetracker.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import edu.uc.kovaciad.slicetracker.R

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    // For whatever reason, it won't compile when I do lateinit for this, but will if I don't
//    private lateinit var applicationViewModel: ApplicationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val applicationViewModel = ViewModelProvider(this).get(ApplicationViewModel::class.java)
        // To UI Designer: This is where you'll find your AutoComplete Data. Just fill it in
        applicationViewModel.brandService.getBrandDAO().getAll().observe(viewLifecycleOwner, Observer {
//                brands ->
        })
        applicationViewModel.printerService.getPrinterDAO().getAll().observe(viewLifecycleOwner, Observer {
//                printers ->
        })

        applicationViewModel.modelService.getModelDAO().getAll().observe(viewLifecycleOwner, Observer {
//                models ->
        })

    }

    companion object {
        fun newInstance() = MainFragment()
    }

}