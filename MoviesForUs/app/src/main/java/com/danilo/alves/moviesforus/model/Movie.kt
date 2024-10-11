package com.danilo.alves.moviesforus.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movie (
    @StringRes val title: Int,
    @StringRes val duration: Int,
    @StringRes val year: Int,
    @StringRes val genre: Int,
    @StringRes val sinopse: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val youtubeVideoId: Int

)
