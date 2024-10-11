package com.danilo.alves.moviesforus.ui

import androidx.lifecycle.ViewModel
import com.danilo.alves.moviesforus.model.GenreMovies
import com.danilo.alves.moviesforus.model.Movie
import com.danilo.alves.moviesforus.model.MoviesForUsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MoviesForUsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        MoviesForUsUiState(currentGenre = GenreMovies.getDefaultGenre())
    )

    val uiState: StateFlow<MoviesForUsUiState> = _uiState

    fun updateSelectedGenre(genre: GenreMovies) {
        _uiState.update {
            it.copy(currentGenre = genre)
        }
    }

    fun updateSelectedMovie(movie: Movie) {
        _uiState.update {
            it.copy(currentMovie = movie)
        }
    }

    fun updateScreenBackUpPressed(currentScreen: String) {
        _uiState.update {
            it.copy(currentScreen = currentScreen)
        }
    }

}