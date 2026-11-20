package com.example.animedex.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.animedex.data.local.dao.AnimeDao
import com.example.animedex.data.local.entity.GenreEntity
import com.example.animedex.data.local.relation.AnimeWithGenres
import com.example.animedex.data.preferences.UserPreferences
import com.example.animedex.data.preferences.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class FilterState(
    val query: String = "",
    val selectedType: String? = null,
    val selectedGenre: GenreEntity? = null,
    val onlyFavorites: Boolean = false
)

data class AnimeDetailState(
    val animeId: Int
)

class AnimeViewModel(
    private val animeDao: AnimeDao,
    private val preferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val userPreferences: StateFlow<UserPreferences> = MutableStateFlow(UserPreferences())
    val filterState: StateFlow<FilterState> = MutableStateFlow(FilterState())
    val animes: StateFlow<List<AnimeWithGenres>> = MutableStateFlow(emptyList())
    val allTypes: StateFlow<List<String>> = MutableStateFlow(emptyList())
    val allGenres: StateFlow<List<GenreEntity>> = MutableStateFlow(emptyList())

    val selectedAnimeId: StateFlow<Int?> = MutableStateFlow(null)
    val selectedAnimeDetail: StateFlow<AnimeDetailState?> = MutableStateFlow(null)

    fun onSearchQueryChange(query: String) {}
    fun onTypeSelected(type: String) {}
    fun onGenreSelected(genre: GenreEntity) {}
    fun onToggleOnlyFavorites(onlyFavorites: Boolean) {}
    fun toggleDarkMode(isDark: Boolean) {}
    fun setSortBy(sortBy: String) {}
    fun toggleFavorite(animeId: Int) {}
    fun onSelectAnime(animeId: Int?) {
        (selectedAnimeId as MutableStateFlow).value = animeId
        (selectedAnimeDetail as MutableStateFlow).value = animeId?.let { AnimeDetailState(it) }
    }

    companion object {
        fun provideFactory(
            animeDao: AnimeDao,
            preferencesRepository: UserPreferencesRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AnimeViewModel(animeDao, preferencesRepository) as T
            }
        }
    }
}
