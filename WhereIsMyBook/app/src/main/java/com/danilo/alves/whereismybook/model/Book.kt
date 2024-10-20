package com.danilo.alves.whereismybook.model

import kotlinx.serialization.Serializable

@Serializable
data class Book (
    val id: String,
    val volumeInfo: VolumeInfo
)