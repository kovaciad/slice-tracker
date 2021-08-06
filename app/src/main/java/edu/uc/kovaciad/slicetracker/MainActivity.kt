package edu.uc.kovaciad.slicetracker

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import edu.uc.kovaciad.slicetracker.ui.main.MainFragment
import edu.uc.kovaciad.slicetracker.ui.main.OverviewFragment
import kotlin.math.abs

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

            return if (abs(diffX) > abs(diffY)) {
                // left or right
                if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
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
                return super.onFling(downEvent, moveEvent, velocityX, velocityY)

            }

        }
    }

    // On swipe right, go back to main page
    private fun onSwipeRight() {
        if (activeFragment == overviewFragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
            activeFragment = mainFragment
        }
    }

    // On swipe left, open the overview page
    private fun onSwipeLeft() {
        if (activeFragment == mainFragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OverviewFragment.newInstance())
                .commitNow()
            activeFragment = overviewFragment
        }
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
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
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
