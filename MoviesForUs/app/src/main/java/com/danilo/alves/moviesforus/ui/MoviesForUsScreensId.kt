package com.danilo.alves.moviesforus.ui

import androidx.annotation.StringRes
import com.danilo.alves.moviesforus.R

enum class MoviesForUsScreensId(@StringRes val title: Int) {
    Genre(title = R.string.select_genre),
    Movies(title = R.string.select_movie),
    Details(title = R.string.details)
}