package edu.uc.kovaciad.slicetracker.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.constraintlayout.widget.Group
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.uc.kovaciad.slicetracker.R
import edu.uc.kovaciad.slicetracker.dto.SliceFile
import kotlinx.coroutines.*
import java.lang.IllegalStateException


class MainFragment : Fragment(), DeleteObject {

    private var selectedMaterialType = ""
    private var currentFilament = false
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    override var slice: SliceFile = SliceFile()

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
        // Bindings kept resulting in unpredictable behavior, so I did it this way

        // Radio Buttons
        val matTypeButton = getView()?.findViewById<RadioGroup>(R.id.material)
        val resinButton = getView()?.findViewById<RadioButton>(R.id.resinRadio)
        val filamentButton = getView()?.findViewById<RadioButton>(R.id.filamentRadio)
        // Text Entries
        val sliceNameEntry = getView()?.findViewById(R.id.sliceNameEntry) as AutoCompleteTextView
        val printerName = getView()?.findViewById<TextView>(R.id.printer)
        val modelName = getView()?.findViewById<TextView>(R.id.modelName)
        val artistName = getView()?.findViewById<TextView>(R.id.artistName)
        val estTime = getView()?.findViewById<TextView>(R.id.filamentEstimatedTime)
        val numberOfLayers = getView()?.findViewById<TextView>(R.id.numberOfLayers)
        val estimatedMaterial = getView()?.findViewById<TextView>(R.id.estimatedMaterial)
        val filamentNozzleThickness = getView()?.findViewById<TextView>(R.id.filamentNozzleThickness)
        val resinBaseLayerCureTime = getView()?.findViewById<TextView>(R.id.resinBaseLayerCureTime)
        val resinBaseLayers = getView()?.findViewById<TextView>(R.id.resinBaseLayers)
        val resinLayerThickness = getView()?.findViewById<TextView>(R.id.resinLayerThickness)
        val resinLiftHeight = getView()?.findViewById<TextView>(R.id.resinLiftHeight)
        val resinLayerCureTime = getView()?.findViewById<TextView>(R.id.resinLayerCureTime)
        val resinLiftSpeed = getView()?.findViewById<TextView>(R.id.resinLiftSpeed)
        val resinRetractSpeed = getView()?.findViewById<TextView>(R.id.resinRetractSpeed)
        // Selector
        val spnSliceSelect = getView()?.findViewById<Spinner>(R.id.spnSliceSelect)
        // Buttons
        val saveButton = getView()?.findViewById<ImageButton>(R.id.saveButton)
        val delButton = getView()?.findViewById<ImageButton>(R.id.deleteButton)

        // Material Radio Buttons. Shows relevant fields for material.
        matTypeButton!!.setOnCheckedChangeListener { _: RadioGroup, id: Int ->
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

        viewModel.sliceFiles.observe(viewLifecycleOwner, {
            slices -> sliceNameEntry.setAdapter(ArrayAdapter(requireContext(),
                    R.layout.support_simple_spinner_dropdown_item, slices))
        })

        viewModel.sliceFiles.observe(viewLifecycleOwner, {
            slices ->
            // Add a blank default value to top of array
            slices.add(0, SliceFile())
            spnSliceSelect!!.adapter = ArrayAdapter(requireContext(),
                R.layout.support_simple_spinner_dropdown_item, slices)
        })

        // The spinner at the top that allows editing/deleting of existing slices
        spnSliceSelect!!.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                slice = parent?.getItemAtPosition(pos) as SliceFile
                // Place all components into fields
                sliceNameEntry.setText(slice.sliceFileName)
                artistName!!.text = slice.artist
                printerName!!.text = slice.printer
                modelName!!.text = slice.model
                numberOfLayers!!.text = slice.numberOfLayers.toString()
                estimatedMaterial!!.text = slice.estimatedMaterial.toString()
                estTime!!.text = slice.estimatedTime.toString()
                if (slice.material == "Resin") {
                    resinButton!!.isChecked = true
                    filamentButton!!.isChecked = false

                    resinBaseLayerCureTime!!.text = slice.resinBaseLayerCureTime.toString()
                    resinBaseLayers!!.text = slice.resinBaseLayers.toString()
                    resinLayerCureTime!!.text = slice.resinLayerCureTime.toString()
                    resinLayerThickness!!.text = slice.resinLayerThickness.toString()
                    resinLiftHeight!!.text = slice.resinLiftHeight.toString()
                    resinLiftSpeed!!.text = slice.resinLiftSpeed.toString()
                    resinRetractSpeed!!.text = slice.resinRetractSpeed.toString()
                } else {
                    resinButton!!.isChecked = false
                    filamentButton!!.isChecked = true

                    filamentNozzleThickness!!.text = slice.filamentNozzleThickness.toString()
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                return // Nothing needs to be done
            }

        }

        // Logic for save button. Collects all relevant data and saves
        saveButton!!.setOnClickListener {
            val sliceFile = SliceFile()
            // Save fields that apply to both filament and resin
            sliceFile.sliceFileName = sliceNameEntry.text.toString()
            sliceFile.printer = printerName!!.text.toString()
            sliceFile.model = modelName!!.text.toString()
            sliceFile.artist = artistName!!.text.toString()

            sliceFile.estimatedTime =
                if (estTime!!.text.isNotEmpty())
                    estTime.text.toString().toDouble() else 0.0

            sliceFile.numberOfLayers = if (numberOfLayers!!.text.toString().isNotEmpty())
                numberOfLayers.text.toString().toInt() else 0

            sliceFile.estimatedMaterial =
                if (estimatedMaterial!!.text.toString().isNotEmpty())
                    estimatedMaterial.text.toString().toDouble() else 0.0

            sliceFile.material = selectedMaterialType
            sliceFile.id = slice.id

            if (selectedMaterialType != "Resin" && (sliceFile.sliceFileName.isNotEmpty()
                        || sliceFile.sliceFileName != "")) { // This extra check is not actually redundant
                // Filament only fields go here to be saved
                sliceFile.filamentNozzleThickness =
                    if (filamentNozzleThickness!!.text.toString().isNotEmpty())
                        filamentNozzleThickness.text.toString().toDouble() else 0.0
                saveSlice(sliceFile)
            } else if (sliceFile.sliceFileName.isNotEmpty()){
                // Resin only fields go here to be saved

                sliceFile.resinBaseLayerCureTime =
                    if (resinBaseLayerCureTime!!.text.toString().isNotEmpty() &&
                        resinBaseLayerCureTime.text.toString().isDigitsOnly())
                            resinBaseLayerCureTime.text.toString().toInt() else 0

                sliceFile.resinBaseLayers =
                    if (resinBaseLayers!!.text.toString().isNotEmpty()
                        && resinBaseLayers.text.toString().isDigitsOnly())
                            resinBaseLayers.text.toString().toInt() else 0

                sliceFile.resinLayerThickness =
                    if (resinLayerThickness!!.text.toString().isNotEmpty())
                        resinLayerThickness.text.toString().toDouble() else 0.0

                sliceFile.resinLiftHeight =
                    if (resinLiftHeight!!.text.isNotEmpty()
                        && resinLiftHeight.text.isDigitsOnly())
                            resinLiftHeight.text.toString().toInt() else 0

                sliceFile.resinLayerCureTime =
                    if (resinLayerCureTime!!.text.isNotEmpty())
                        resinLayerCureTime.text.toString().toDouble() else 0.0

                sliceFile.resinLiftSpeed =
                    if (resinLiftSpeed!!.text.toString().isNotEmpty()
                        && resinLiftSpeed.text.toString().isDigitsOnly())
                            resinLiftSpeed.text.toString().toInt() else 0
                sliceFile.resinRetractSpeed =
                    if (resinRetractSpeed!!.text.toString().isNotEmpty()
                        && resinRetractSpeed.text.toString().isDigitsOnly())
                            resinRetractSpeed.text.toString().toInt() else 0
                saveSlice(sliceFile)

            } else {
                Toast.makeText(context, "No Slice File Selected!", Toast.LENGTH_LONG)
                    .show()
            }
        }

        delButton!!.setOnClickListener {
            if (slice.id != "") {
                val delDialogFragment = DeleteConfirmationFragment(slice, this)
                delDialogFragment.show(parentFragmentManager, "Delete Plant")
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * @param sliceFile: SliceFile to be saved
     * Saves passed slice and displays a toast telling the user if it succeeded
     */
    private fun saveSlice(sliceFile: SliceFile) {
        uiScope.launch {
            val saved: Boolean = viewModel.save(sliceFile)
            withContext(Dispatchers.Main) {
                if (saved) {
                    Toast.makeText(context, "${sliceFile.sliceFileName} saved!",
                        Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(context, "${sliceFile.sliceFileName} not saved. :(",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }

    /**
     * @param sliceFile: SliceFile to be deleted
     * Deletes slice and displays a toast telling the user if it succeeded
     */
    override fun deleteSlice(sliceFile: SliceFile) {
        uiScope.launch {
            val deleted = viewModel.delete(sliceFile)
            withContext(Dispatchers.Main) {
                if (deleted) {
                    Toast.makeText(context, "${sliceFile.sliceFileName} deleted!",
                        Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(context, "${sliceFile.sliceFileName} not deleted. :(",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    /**
     * This fragment will create a dialog confirming whether the user wants to delete the
     * currently selected sliceFile. If they click "DELETE", the slice will be irrevocably deleted
     * @param sliceFile: Slice to be deleted
     * @param objectDeleted: Calling Fragment which must contain a delete function
     */
    class DeleteConfirmationFragment(private val sliceFile: SliceFile,
                                     private val objectDeleted: DeleteObject) : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return activity?.let {
                val builder = AlertDialog.Builder(it)
                val inflater = requireActivity().layoutInflater
                val delSliceView = inflater.inflate(R.layout.deleteslicedialog, null)
                builder.setView(delSliceView)
                    .setPositiveButton(getString(R.string.delete)) { _, _ ->
                        objectDeleted.deleteSlice(sliceFile)
                        dialog?.cancel()
                    }
                    .setNegativeButton(getString(R.string.cancel)) { _, _ ->
                        dialog?.cancel()
                    }
                builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}

/**
 * Interface for object that calls a delete on a slice file
 */
interface DeleteObject {
    var slice: SliceFile
    fun deleteSlice(sliceFile: SliceFile)
}