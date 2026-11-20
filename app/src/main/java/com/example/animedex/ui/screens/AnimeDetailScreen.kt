package com.example.animedex.ui.screens

import androidx.compose.runtime.Composable
import com.example.animedex.ui.AnimeDetailState

@Composable
fun AnimeDetailScreen(
    detailState: AnimeDetailState?,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onBack: () -> Unit
) {
    // Dummy UI
}
