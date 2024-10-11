package com.danilo.alves.moviesforus.model

import com.danilo.alves.moviesforus.data.MoviesLocalDataProvider
import com.danilo.alves.moviesforus.ui.MoviesForUsScreensId

data class MoviesForUsUiState (
    val currentGenre: GenreMovies = GenreMovies.getDefaultGenre(),
    val currentScreen: String = MoviesForUsScreensId.Genre.name,
    val currentMovie: Movie = MoviesLocalDataProvider.getAllMoviesData().get(0)
)
