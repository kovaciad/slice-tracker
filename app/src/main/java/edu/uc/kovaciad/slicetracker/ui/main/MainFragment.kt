package edu.uc.kovaciad.slicetracker.ui.main

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.firebase.ui.auth.util.data.AuthOperationManager.getInstance
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
        savedInstanceState: Bundle?,
    ): View {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.main_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        var sliceDb : SliceDatabase
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        val applicationViewModel = ViewModelProvider(this).get(ApplicationViewModel::class.java)


        // To UI Designer: This is where you'll find your AutoComplete Data. Just fill it in
      //  applicationViewModel.brandService.getBrandDAO().getAllBrands().observe(viewLifecycleOwner, Observer {
//                brands ->
        //    Log.d(it.toString(), it.toString())
      //  })
      //  var queryString : String = "SELECT * FROM Brand"
      //  val query = SimpleSQLiteQuery(queryString)

       // applicationViewModel.databaseService.getRoomDB().queryExecutor.query(query)

       // applicationViewModel.modelService.getModelDAO().getAll().observe(viewLifecycleOwner, Observer {
//                models ->
       // })



    }


}