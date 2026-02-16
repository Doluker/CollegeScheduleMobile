package com.example.collegeschedulemobile.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "favorites")
class FavoritesManager(private val context: Context) {
    private val FAVORITES_KEY = stringSetPreferencesKey("favorite_groups")
    val favorites: Flow<Set<String>> = context.dataStore.data.map { prefs ->
        prefs[FAVORITES_KEY] ?: emptySet()
    }
    suspend fun toggleFavorite(groupName: String) {
        context.dataStore.edit { prefs ->
            val current = prefs[FAVORITES_KEY] ?: emptySet()
            if (current.contains(groupName)) {
                prefs[FAVORITES_KEY] = current - groupName
            } else {
                prefs[FAVORITES_KEY] = current + groupName
            }
        }
    }
}