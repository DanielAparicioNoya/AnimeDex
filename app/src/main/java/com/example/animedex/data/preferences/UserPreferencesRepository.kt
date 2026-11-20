package com.example.animedex.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "animedex_prefs")

data class UserPreferences(
    val isDarkMode: Boolean = true,
    val sortBy: String = "RANK",
    val favoriteAnimeIds: Set<Int> = emptySet()
)

class UserPreferencesRepository(private val context: Context) {

    private object Keys {
        val DARK_MODE = booleanPreferencesKey("dark_mode")
        val SORT_BY = stringPreferencesKey("sort_by")
        val FAVORITE_IDS = stringSetPreferencesKey("favorite_anime_ids")
    }

    val userPreferencesFlow: Flow<UserPreferences> = context.dataStore.data.map { prefs ->
        val favIds = prefs[Keys.FAVORITE_IDS]
            ?.mapNotNull { it.toIntOrNull() }
            ?.toSet()
            ?: emptySet()

        UserPreferences(
            isDarkMode = prefs[Keys.DARK_MODE] ?: true,
            sortBy = prefs[Keys.SORT_BY] ?: "RANK",
            favoriteAnimeIds = favIds
        )
    }

    suspend fun toggleDarkMode(currentValue: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[Keys.DARK_MODE] = !currentValue
        }
    }

    suspend fun setSortBy(sortBy: String) {
        context.dataStore.edit { prefs ->
            prefs[Keys.SORT_BY] = sortBy
        }
    }

    suspend fun toggleFavorite(animeId: Int) {
        context.dataStore.edit { prefs ->
            val current = prefs[Keys.FAVORITE_IDS]?.toMutableSet() ?: mutableSetOf()
            val key = animeId.toString()
            if (current.contains(key)) {
                current.remove(key)
            } else {
                current.add(key)
            }
            prefs[Keys.FAVORITE_IDS] = current
        }
    }
}
