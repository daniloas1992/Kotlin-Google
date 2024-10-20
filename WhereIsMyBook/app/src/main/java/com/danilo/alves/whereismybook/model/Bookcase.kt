package com.danilo.alves.whereismybook.model

import kotlinx.serialization.Serializable

@Serializable
data class Bookcase (
    val totalItems: Int,
    val items: List<Book>
)