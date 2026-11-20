package com.example.animedex.ui.screens

import androidx.compose.runtime.Composable
import com.example.animedex.data.local.entity.GenreEntity
import com.example.animedex.data.local.relation.AnimeWithGenres
import com.example.animedex.data.preferences.UserPreferences
import com.example.animedex.ui.FilterState

@Composable
fun AnimeListScreen(
    animes: List<AnimeWithGenres>,
    allTypes: List<String>,
    allGenres: List<GenreEntity>,
    filterState: FilterState,
    userPreferences: UserPreferences,
    onSearchQueryChange: (String) -> Unit,
    onTypeSelected: (String) -> Unit,
    onGenreSelected: (GenreEntity) -> Unit,
    onToggleOnlyFavorites: (Boolean) -> Unit,
    onToggleDarkMode: (Boolean) -> Unit,
    onSortByChange: (String) -> Unit,
    onToggleFavorite: (Int) -> Unit,
    onAnimeClick: (Int) -> Unit
) {
    // Dummy UI
}
