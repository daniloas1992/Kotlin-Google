package com.danilo.alves.whereismybook.model

import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo (
    val title: String,
    val subtitle: String? = "",
    val publishedDate: String? = "",
    val pageCount: Int? = 0,
    val imageLinks: ImageLinks = ImageLinks(),
    val language: String? = "",
    val description: String? = ""
)
