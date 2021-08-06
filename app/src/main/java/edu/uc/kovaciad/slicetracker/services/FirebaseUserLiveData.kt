package edu.uc.kovaciad.slicetracker.services

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Allows us to get LiveData of our current user state
 */
class FirebaseUserLiveData : LiveData<FirebaseUser?>() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    /**
     * Listener variable that watches current user state
     */
    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        value = firebaseAuth.currentUser
    }

    /**
     * Adds an observer to listen for user state in FirebaseAuth
     */
    override fun onActive() {
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    /**
     * If we do not have an observer, we should remove the listener
     */
    override fun onInactive() {
        firebaseAuth.removeAuthStateListener(authStateListener)
    }
}