package com.example.animedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animedex.data.local.AnimeDatabase
import com.example.animedex.data.preferences.UserPreferencesRepository
import com.example.animedex.ui.AnimeViewModel
import com.example.animedex.ui.screens.AnimeDetailScreen
import com.example.animedex.ui.screens.AnimeListScreen
import com.example.animedex.ui.theme.AnimeDexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AnimeDatabase.getDatabase(applicationContext)
        val preferencesRepository = UserPreferencesRepository(applicationContext)

        setContent {
            val viewModel: AnimeViewModel = viewModel(
                factory = AnimeViewModel.provideFactory(
                    animeDao = database.animeDao(),
                    preferencesRepository = preferencesRepository
                )
            )

            val userPreferences by viewModel.userPreferences.collectAsState()
            val filterState by viewModel.filterState.collectAsState()
            val animes by viewModel.animes.collectAsState()
            val allTypes by viewModel.allTypes.collectAsState()
            val allGenres by viewModel.allGenres.collectAsState()
            val selectedAnimeId by viewModel.selectedAnimeId.collectAsState()
            val detailState by viewModel.selectedAnimeDetail.collectAsState()

            AnimeDexTheme(darkTheme = userPreferences.isDarkMode) {
                if (selectedAnimeId != null) {
                    BackHandler {
                        viewModel.onSelectAnime(null)
                    }
                    AnimeDetailScreen(
                        detailState = detailState,
                        isFavorite = userPreferences.favoriteAnimeIds.contains(selectedAnimeId),
                        onToggleFavorite = {
                            selectedAnimeId?.let { viewModel.toggleFavorite(it) }
                        },
                        onBack = { viewModel.onSelectAnime(null) }
                    )
                } else {
                    AnimeListScreen(
                        animes = animes,
                        allTypes = allTypes,
                        allGenres = allGenres,
                        filterState = filterState,
                        userPreferences = userPreferences,
                        onSearchQueryChange = viewModel::onSearchQueryChange,
                        onTypeSelected = viewModel::onTypeSelected,
                        onGenreSelected = viewModel::onGenreSelected,
                        onToggleOnlyFavorites = viewModel::onToggleOnlyFavorites,
                        onToggleDarkMode = viewModel::toggleDarkMode,
                        onSortByChange = viewModel::setSortBy,
                        onToggleFavorite = viewModel::toggleFavorite,
                        onAnimeClick = viewModel::onSelectAnime
                    )
                }
            }
        }
    }
}
