package edu.uc.kovaciad.slicetracker.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uc.kovaciad.slicetracker.R
import edu.uc.kovaciad.slicetracker.dto.SliceFile
import kotlin.math.roundToLong
import androidx.lifecycle.Observer

class OverviewFragment : SuperFragment() {

    private var totalPrintTime: Double = 0.0
    private var totalMats: Double = 0.0
    private lateinit var applicationViewModel: ApplicationViewModel
    private val LOC_REQUEST_CODE = 2000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.overview_fragment, container, false)
    }

    private fun prepLocationUpdates() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            requestLocationUpdates()
        } else {
            val permissionRequest = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
            requestPermissions(permissionRequest, LOC_REQUEST_CODE)
        }
    }

    private fun requestLocationUpdates() {
        val cityView = requireView().findViewById<TextView>(R.id.cityView)
        val res = resources
        applicationViewModel = ViewModelProvider(this).get(ApplicationViewModel::class.java)
        applicationViewModel.getLocationLiveData().observe(viewLifecycleOwner, {
            val latitudeValue: Double = it.latitude.toDouble()
            val longitudeValue: Double = it.longitude.toDouble()
            val geocoder = Geocoder(context)
            val list = geocoder.getFromLocation(latitudeValue, longitudeValue, 1)
            var currentCity = ""
            if (list[0].locality != null) {
                currentCity = list[0].locality

            }

            val mText = String.format(res.getString(R.string.currentCity), currentCity)
            cityView.text = mText
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actTotalPrintTime = getView()?.findViewById<TextView>(R.id.actTotalPrintTime)
        val actTotalMaterials = getView()?.findViewById<TextView>(R.id.actTotalMaterial)
        val rcySlices = getView()?.findViewById<RecyclerView>(R.id.rcySlices)
        var slices: ArrayList<SliceFile>
        /*
        This observer is a workaround for a bug where the value of the LiveData would be null on start
        It is not elegant, but it allows this to work as it waits for a value.
         */

        prepLocationUpdates()
        viewModel.sliceFiles.observe(viewLifecycleOwner, {
                sliceFiles ->
                    slices = sliceFiles
                    rcySlices!!.adapter = SliceAdapter(slices, R.layout.rootlayout)
        })
        rcySlices!!.hasFixedSize()
        rcySlices.layoutManager = LinearLayoutManager(context)
        rcySlices.itemAnimator = DefaultItemAnimator()

        val res = resources
        viewModel.sliceFiles.observe(viewLifecycleOwner, {
            sliceFiles ->
                totalPrintTime = 0.0
                totalMats = 0.0
                sliceFiles.forEach {
                    totalPrintTime += it.estimatedTime
                    totalMats += it.estimatedMaterial
                }

                val tText = String.format(res.getString(R.string.totalTime), totalPrintTime.roundToLong().toString())
                val mText = String.format(res.getString(R.string.totalMats), totalMats.roundToLong().toString())
                actTotalPrintTime?.text = tText
                actTotalMaterials?.text = mText
        })

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            LOC_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==  PackageManager.PERMISSION_GRANTED) {
                    requestLocationUpdates()
                } else {
                    Toast.makeText(
                        context,
                        "Unable to update location without permission",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else -> {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    inner class SliceAdapter(private val slices: ArrayList<SliceFile>, private val itemLayout: Int) : RecyclerView.Adapter<OverviewFragment.SliceViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliceViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
            return SliceViewHolder(view)
        }

        override fun onBindViewHolder(holder: SliceViewHolder, position: Int) {
            val slice = slices[position]
            holder.updateSlices(slice)
        }

        override fun getItemCount(): Int {
            return slices.size
        }

    }

    inner class SliceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var lblSlice: TextView = itemView.findViewById(R.id.lblSlice)
        private var lblTime: TextView = itemView.findViewById(R.id.lblTime)
        private var lblMats: TextView = itemView.findViewById(R.id.lblMats)

        fun updateSlices(sliceFile: SliceFile) {
            val res = resources
            lblSlice.text = sliceFile.sliceFileName
            lblTime.text = String.format(res.getString(R.string.totalTime),
                sliceFile.estimatedTime.toString())
            lblMats.text = String.format(res.getString(R.string.totalMats),
                sliceFile.estimatedMaterial.toString())
        }
    }

    companion object {
        fun newInstance() = OverviewFragment()
    }

}