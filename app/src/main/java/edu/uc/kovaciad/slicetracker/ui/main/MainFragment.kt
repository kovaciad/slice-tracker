package edu.uc.kovaciad.slicetracker.ui.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.constraintlayout.widget.Group
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import edu.uc.kovaciad.slicetracker.R
import edu.uc.kovaciad.slicetracker.dto.SliceFile
import kotlinx.coroutines.*
import java.lang.IllegalStateException


class MainFragment : Fragment(), NewSliceCreated {

    var selectedMaterialType = ""
    var currentFilament = false
    override val job = Job()
    override val uiScope = CoroutineScope(Dispatchers.Main + job)
    private var _sliceId = ""
    private var selectedSlice: SliceFile = SliceFile()

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

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Bindings kept resulting in unpredictable behavior, so I did it this way
        val matTypeButton = getView()?.findViewById<RadioGroup>(R.id.material)
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

        // Material Radio Buttons. Shows relevant fields for material.
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



        viewModel.sliceFiles.observe(viewLifecycleOwner, Observer {
            slices -> sliceNameEntry.setAdapter(ArrayAdapter(requireContext(),
                    R.layout.support_simple_spinner_dropdown_item, slices))
        })

        sliceNameEntry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedSlice = SliceFile()
            }

            /**
             * Prexisting item selected from autocomplete list.
             */
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                selectedSlice = parent!!.getItemAtPosition(pos) as SliceFile
                _sliceId = selectedSlice.id
            }
        }

        sliceNameEntry.setOnFocusChangeListener { view, hasFocus ->
            val sliceEntered = sliceNameEntry.text.toString()
            if (sliceEntered.isNotEmpty() && sliceEntered != selectedSlice.toString()) {
                val newSliceDialogFragment = NewSliceDialogFragment(sliceEntered, this)
                newSliceDialogFragment.show(parentFragmentManager, "New Slice")
            }
        }

        // Logic for save button. Collects all relevant data and saves
        val saveButton = getView()?.findViewById<ImageButton>(R.id.saveButton)
        saveButton!!.setOnClickListener {
            val sliceFile = SliceFile()
            // Save fields that apply to both filament and resin
            sliceFile.sliceFileName = sliceNameEntry.text.toString()
            sliceFile.printer = printerName!!.text.toString()
            sliceFile.printer = modelName!!.text.toString()
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
            sliceFile.id = _sliceId

            if (selectedMaterialType != "Resin" && sliceFile.sliceFileName.isNotEmpty()) {
                // Filament only fields go here to be saved
                sliceFile.filamentNozzleThickness =
                    if (filamentNozzleThickness!!.text.toString().isNotEmpty())
                        filamentNozzleThickness.text.toString().toDouble() else 0.0
                uiScope.launch(Dispatchers.IO) {
                    saveSlice(sliceFile)
                }
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
    override suspend fun saveSlice(sliceFile: SliceFile) {
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

    class NewSliceDialogFragment (private val sliceEntered: String, private val newSliceCreated: NewSliceCreated) : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return activity?.let {
                val builder = AlertDialog.Builder(it)
                val inflater = requireActivity().layoutInflater
                val newSliceView = inflater.inflate(R.layout.newslicedialog, null)
                val sliceName = newSliceView.findViewById<EditText>(R.id.newSlice)
                val estTime = newSliceView.findViewById<EditText>(R.id.newEstTime)
                val estMats = newSliceView.findViewById<EditText>(R.id.newEstMat)
                sliceName.setText(sliceEntered)
                builder.setView(newSliceView)
                    .setPositiveButton(getString(R.string.save)) { dialog, which ->
                        val name = sliceName.text.toString()
                        val time = if (estTime.text.isNotEmpty())
                            estTime.text.toString().toDouble() else 0.0
                        val mats = if (estMats.text.isNotEmpty())
                            estMats.text.toString().toDouble() else 0.0
                        val newSlice = SliceFile().apply {
                            sliceFileName = name
                            estimatedTime = time
                            estimatedMaterial = mats
                        }
                        newSliceCreated.uiScope.launch {
                            newSliceCreated.saveSlice(newSlice)
                        }

                    }
                    .setNegativeButton(getString(R.string.cancel)) { dialog, which ->
                        getDialog()?.cancel()
                    }
                builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }


}

interface NewSliceCreated {
    val job: Job
    val uiScope: CoroutineScope
    suspend fun saveSlice(sliceFile: SliceFile)
}