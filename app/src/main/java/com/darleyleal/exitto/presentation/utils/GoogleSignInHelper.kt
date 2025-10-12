package com.darleyleal.exitto.presentation.utils

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Helper class for Google Sign-In functionality.
 * Handles the Google Sign-In flow using Google Identity Services.
 * Following Clean Architecture principles by separating UI concerns.
 */
@Singleton
class GoogleSignInHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val oneTapClient: SignInClient = Identity.getSignInClient(context)
    
    /**
     * Initiates Google Sign-In flow.
     * 
     * @param launcher ActivityResultLauncher to handle the sign-in result
     */
    fun signIn(launcher: ActivityResultLauncher<IntentSenderRequest>) {
        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(com.darleyleal.exitto.R.string.default_web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()

        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener { result ->
                try {
                    val intentSenderRequest = IntentSenderRequest.Builder(result.pendingIntent.intentSender).build()
                    launcher.launch(intentSenderRequest)
                } catch (e: Exception) {
                    // Handle error
                }
            }
            .addOnFailureListener { 
                // Handle failure
            }
    }
    
    /**
     * Extracts ID token from the Google Sign-In result.
     * 
     * @param data Intent data from the sign-in result
     * @return ID token string or null if extraction fails
     */
    fun getSignInResult(data: Intent?): String? {
        return try {
            val credential = oneTapClient.getSignInCredentialFromIntent(data)
            credential.googleIdToken
        } catch (e: ApiException) {
            null
        }
    }
}

