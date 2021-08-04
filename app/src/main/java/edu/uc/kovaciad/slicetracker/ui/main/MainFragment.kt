package edu.uc.kovaciad.slicetracker.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.constraintlayout.widget.Group
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.uc.kovaciad.slicetracker.R
import edu.uc.kovaciad.slicetracker.dto.SliceFile
import kotlinx.coroutines.*


class MainFragment : Fragment() {

    var selectedMaterialType = ""
    var currentFilament = false
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

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
        val matTypeButton = getView()?.findViewById<RadioGroup>(R.id.material)

        matTypeButton!!.setOnCheckedChangeListener { group: RadioGroup, id: Int ->
            selectedMaterialType = when (id) {
                R.id.filamentRadio -> "Filament"
                R.id.resinRadio -> "Resin"
                else -> "Error"
            }
            if (selectedMaterialType == "Resin") {
                Log.d("VISIBILITY", "RESIN SELECTED")
                // Resin selected, hide filament-related items
                if (currentFilament) {
                    val resinGroup = getView()?.findViewById<Group>(R.id.resinGroup)
                    val filGroup = getView()?.findViewById<Group>(R.id.filamentGroup)
                    resinGroup!!.visibility = View.VISIBLE
                    filGroup!!.visibility = View.INVISIBLE
                    filGroup.invalidate()
                    resinGroup.invalidate()
                    currentFilament = false
                }
            } else {
                Log.d("VISIBILITY", "FILAMENT SELECTED")
                // Filament mat-type selected, hide resin-related items
                if (!currentFilament) {
                    val resinGroup = getView()?.findViewById<Group>(R.id.resinGroup)
                    val filGroup = getView()?.findViewById<Group>(R.id.filamentGroup)
                    resinGroup!!.visibility = View.INVISIBLE
                    filGroup!!.visibility = View.VISIBLE
                    filGroup.invalidate()
                    resinGroup.invalidate()
                    currentFilament = true
                }

            }
        }

        val saveButton = getView()?.findViewById<ImageButton>(R.id.saveButton)
        saveButton!!.setOnClickListener {
            val sliceFile = SliceFile()
            // Save fields that apply to both filament and resin
            val sliceFileName = getView()?.findViewById<TextView>(R.id.sliceNameEntry)
            sliceFile.sliceFileName = sliceFileName!!.text.toString()
            val printerName = getView()?.findViewById<TextView>(R.id.printer)
            sliceFile.printer = printerName!!.text.toString()
            val modelName = getView()?.findViewById<TextView>(R.id.modelName)
            sliceFile.printer = modelName!!.text.toString()
            val artistName = getView()?.findViewById<TextView>(R.id.artistName)
            sliceFile.artist = artistName!!.text.toString()
            val estTime =
                getView()?.findViewById<TextView>(R.id.filamentEstimatedTime)
            sliceFile.estimatedTime =
                if (estTime!!.text.isNotEmpty())
                    estTime.text.toString().toDouble() else 0.0
            val numberOfLayers = getView()?.findViewById<TextView>(R.id.numberOfLayers)
            sliceFile.numberOfLayers = if (numberOfLayers!!.text.toString().isNotEmpty())
                numberOfLayers.text.toString().toInt() else 0
            val estimatedMaterial =
                getView()?.findViewById<TextView>(R.id.estimatedMaterial)
            sliceFile.estimatedMaterial =
                if (estimatedMaterial!!.text.toString().isNotEmpty())
                    estimatedMaterial.text.toString().toDouble() else 0.0
            sliceFile.material = selectedMaterialType

            if (selectedMaterialType != "Resin" && sliceFile.sliceFileName.isNotEmpty()) {
                // Filament only fields go here to be saved
                val filamentNozzleThickness =
                    getView()?.findViewById<TextView>(R.id.filamentNozzleThickness)
                sliceFile.filamentNozzleThickness =
                    if (filamentNozzleThickness!!.text.toString().isNotEmpty())
                        filamentNozzleThickness.text.toString().toDouble() else 0.0
                uiScope.launch(Dispatchers.IO) {
                    saveSlice(sliceFile)
                }
            } else if (sliceFile.sliceFileName.isNotEmpty()){
                // Resin only fields go here to be saved
                val resinBaseLayerCureTime = getView()?.findViewById<TextView>(R.id.resinBaseLayerCureTime)
                sliceFile.resinBaseLayerCureTime =
                    if (resinBaseLayerCureTime!!.text.toString().isNotEmpty() &&
                        resinBaseLayerCureTime.text.toString().isDigitsOnly())
                            resinBaseLayerCureTime.text.toString().toInt() else 0
                val resinBaseLayers = getView()?.findViewById<TextView>(R.id.resinBaseLayers)
                sliceFile.resinBaseLayers =
                    if (resinBaseLayers!!.text.toString().isNotEmpty()
                        && resinBaseLayers.text.toString().isDigitsOnly())
                            resinBaseLayers.text.toString().toInt() else 0
                val resinLayerThickness = getView()?.findViewById<TextView>(R.id.resinLayerThickness)
                sliceFile.resinLayerThickness =
                    if (resinLayerThickness!!.text.toString().isNotEmpty())
                        resinLayerThickness.text.toString().toDouble() else 0.0
                val resinLiftHeight = getView()?.findViewById<TextView>(R.id.resinLiftHeight)
                sliceFile.resinLiftHeight =
                    if (resinLiftHeight!!.text.isNotEmpty()
                        && resinLiftHeight.text.isDigitsOnly())
                            resinLiftHeight.text.toString().toInt() else 0
                val resinLayerCureTime = getView()?.findViewById<TextView>(R.id.resinLayerCureTime)
                sliceFile.resinLayerCureTime =
                    if (resinLayerCureTime!!.text.isNotEmpty())
                        resinLayerCureTime.text.toString().toDouble() else 0.0
                val resinLiftSpeed = getView()?.findViewById<TextView>(R.id.resinLiftSpeed)
                sliceFile.resinLiftSpeed =
                    if (resinLiftSpeed!!.text.toString().isNotEmpty()
                        && resinLiftSpeed.text.toString().isDigitsOnly())
                            resinLiftSpeed.text.toString().toInt() else 0
                val resinRetractSpeed = getView()?.findViewById<TextView>(R.id.resinRetractSpeed)
                sliceFile.resinRetractSpeed =
                    if (resinRetractSpeed!!.text.toString().isNotEmpty()
                        && resinRetractSpeed.text.toString().isDigitsOnly())
                            resinRetractSpeed.text.toString().toInt() else 0
                uiScope.launch(Dispatchers.IO) {
                    saveSlice(sliceFile)
                }
            } else {
                Toast.makeText(context, "No Slice File Selected!", Toast.LENGTH_LONG)
                    .show()
            }
        }


        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * @param sliceFile SliceFile to be saved
     * Calls save in mvm and displays a toast
     */
    private suspend fun saveSlice(sliceFile: SliceFile) {
        val saved: Boolean = viewModel.save(sliceFile)
        withContext(Dispatchers.Main) {
            if (saved) {
                Toast.makeText(context, "${sliceFile.sliceFileName} saved!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(context, "${sliceFile.sliceFileName} not saved. :(", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }


}