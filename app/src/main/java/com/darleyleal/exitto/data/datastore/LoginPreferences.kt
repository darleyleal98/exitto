package com.darleyleal.exitto.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

const val PREFERENCES_NAME = "login_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class LoginPreferences(private val context: Context) {

    companion object {
        val IS_LOGGED_IN_KEY = stringPreferencesKey("is_logged_in")
    }

    suspend fun writeToDataStore(value: String) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = value
        }
    }

    fun readFromDataStore(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN_KEY] ?: ""
        }
    }

    suspend fun clearData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}