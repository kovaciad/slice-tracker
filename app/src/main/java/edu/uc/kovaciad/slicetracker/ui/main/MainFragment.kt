package edu.uc.kovaciad.slicetracker.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Button
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
        // Synthetics aren't working at the moment and neither is data binding
        val btnSaveTest = getView()?.findViewById<Button>(R.id.btnSaveTest)

        btnSaveTest?.setOnClickListener {
            viewModel.save(Printer("Ender 3 Pro", "asduy324", "kjdfsh2", ), "printers")
        }
        super.onViewCreated(view, savedInstanceState)
    }


}