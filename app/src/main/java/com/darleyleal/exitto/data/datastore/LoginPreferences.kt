package com.darleyleal.exitto.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PREFERENCES_NAME = "login_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

/**
 * Handles user login persistence using Jetpack DataStore.
 * Stores a simple boolean flag to indicate if the user is logged in.
 * Provides safe defaults to avoid false positives after reinstallations.
 */
class LoginPreferences(private val context: Context) {

    companion object {
        private val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
    }

    /**
     * Persists the user's login state.
     */
    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = isLoggedIn
        }
    }

    /**
     * Reads the current login state as a Flow.
     * Defaults to false if key not found (first run or after uninstall).
     */
    fun isUserLoggedIn(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            try {
                preferences[IS_LOGGED_IN_KEY] ?: false
            } catch (e: ClassCastException) {
                context.dataStore.edit { it.clear() }
                false
            }
        }
    }

    /**
     * Clears all stored preferences, used on logout.
     */
    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}