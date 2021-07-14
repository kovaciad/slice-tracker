package edu.uc.kovaciad.slicetracker.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.room.Room
import edu.uc.kovaciad.slicetracker.R
import edu.uc.kovaciad.slicetracker.dao.SliceDatabase
import edu.uc.kovaciad.slicetracker.dto.Brand

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    // For whatever reason, it won't compile when I do lateinit for this, but will if I don't
//    private lateinit var applicationViewModel: ApplicationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.main_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var sliceDb : SliceDatabase
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val applicationViewModel = ViewModelProvider(this).get(ApplicationViewModel::class.java)


        // To UI Designer: This is where you'll find your AutoComplete Data. Just fill it in
      //  applicationViewModel.brandService.getBrandDAO().getAllBrands().observe(viewLifecycleOwner, Observer {
//                brands ->
        //    Log.d(it.toString(), it.toString())
      //  })
        applicationViewModel.printerService.getPrinterDAO().getAll().observe(viewLifecycleOwner, Observer {
//                printers ->
        })

        applicationViewModel.modelService.getModelDAO().getAll().observe(viewLifecycleOwner, Observer {
//                models ->
        })


    }


}