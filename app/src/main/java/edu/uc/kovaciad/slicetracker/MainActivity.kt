package edu.uc.kovaciad.slicetracker

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.sqlite.db.SimpleSQLiteQuery
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import edu.uc.kovaciad.slicetracker.ui.main.MainFragment
import edu.uc.kovaciad.slicetracker.ui.main.MainViewModel
import edu.uc.kovaciad.slicetracker.ui.main.OverviewFragment


class MainActivity : AppCompatActivity() {

    private lateinit var detector: GestureDetectorCompat
    private var user: FirebaseUser? = null
    private lateinit var mainFragment: MainFragment
    private lateinit var overviewFragment: Fragment
    private lateinit var activeFragment: Fragment

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mainFragment = MainFragment.newInstance()
        overviewFragment = OverviewFragment.newInstance()
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, mainFragment)
                .commitNow()
            activeFragment = mainFragment
        }

        detector = GestureDetectorCompat(this, SliceGestureListener())

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (detector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    inner class SliceGestureListener : GestureDetector.SimpleOnGestureListener() {

        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(
            downEvent: MotionEvent?,
            moveEvent: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val diffX = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
            val diffY = moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F

            return if (Math.abs(diffX) > Math.abs(diffY)) {
                // left or right
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX < 0) {
                        // Swipe left
                        this@MainActivity.onSwipeLeft()
                    } else {
                        // Swipe right
                        this@MainActivity.onSwipeRight()
                    }
                    true
                } else {
                    return super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }

            } else {
                // top or bottom
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY < 0) {
                        // Swipe bottom
                        this@MainActivity.onSwipeBottom()
                    }
                    true
                } else {
                    return super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }

        }
    }

    // On swipe right, go back to main page
    internal fun onSwipeRight() {
        if (activeFragment == overviewFragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
            activeFragment = mainFragment
        }
    }

    // On swipe left, open the overview page
    internal fun onSwipeLeft() {
        if (activeFragment == mainFragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OverviewFragment.newInstance())
                .commitNow()
            activeFragment = overviewFragment
        }
    }

    // For future use
    private fun onSwipeBottom() {
        Toast.makeText(applicationContext, "Swiped bottom (Will be something useful probably)", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        // Check to see if there is an account logged in to decide what to show
        if (user != null) {
            // User logged in
            menu!!.findItem(R.id.loginButton).isVisible = false
        } else {
            // User not logged in
            menu!!.findItem(R.id.signOutBtn).isVisible = false
        }
        return true
    }

    // Handle buttons in the top menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.loginButton -> {
            createSignInIntent()
            true
        }
        R.id.signOutBtn -> {
            signOut()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            // Sign in success
            user = FirebaseAuth.getInstance().currentUser
            // Reload options menu to reset options menu
            invalidateOptionsMenu()
        } else {
            // Sign in failure
            Log.w("FIREBASE", "LOGIN FAILURE")
        }
    }

    // Sign into Firebase
    private fun createSignInIntent() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    // Sign out of Firebase
    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
        user = null
        // Reload options menu to reset options menu
        invalidateOptionsMenu()
    }

}
