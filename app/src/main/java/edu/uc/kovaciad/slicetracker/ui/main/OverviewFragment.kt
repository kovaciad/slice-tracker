package edu.uc.kovaciad.slicetracker.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import edu.uc.kovaciad.slicetracker.R

class OverviewFragment : SuperFragment() {

    companion object {
        fun newInstance() = OverviewFragment()
    }

    private var totalPrintTime: Double = 0.0
    private var totalMats: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val actTotalPrintTime = getView()?.findViewById<TextView>(R.id.actTotalPrintTime)
        val actTotalMaterials = getView()?.findViewById<TextView>(R.id.actTotalMaterial)
        val overviewList = getView()?.findViewById<ListView>(R.id.overviewList)
        viewModel.sliceFiles.observe(viewLifecycleOwner, {
            sliceFiles ->
                overviewList?.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, sliceFiles)
        })

        val res = resources
        viewModel.sliceFiles.observe(viewLifecycleOwner, {
            sliceFiles ->
                totalPrintTime = 0.0
                sliceFiles.forEach {
                    totalPrintTime += it.filamentNozzleEstimatedTime
                }

                val text = String.format(res.getString(R.string.totalTime), totalPrintTime.toString())
                actTotalPrintTime?.text = text
        })

        viewModel.sliceFiles.observe(viewLifecycleOwner, {
            sliceFiles ->
                totalMats = 0.0
                sliceFiles.forEach {
                    totalMats += it.filamentEstimatedMaterial
                }
                val text = String.format(res.getString(R.string.totalMats), totalMats.toString())
                actTotalMaterials?.text = text
        })
    }

}