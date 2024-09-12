package com.danilo.alves.listas.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic (
    @StringRes val stringResourceId: Int,
    val totalCourses: Int,
    @DrawableRes val imageResourceId: Int
)
