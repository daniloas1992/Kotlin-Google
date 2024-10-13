package com.danilo.alves.amphibians.data

import com.danilo.alves.amphibians.model.Amphibian
import com.danilo.alves.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibians() : List<Amphibian>
}

class NetworkAmphibiansRepository (private val amphibiansApiService: AmphibiansApiService) : AmphibiansRepository {

    override suspend fun getAmphibians(): List<Amphibian> {
        return amphibiansApiService.getAmphibians()
    }

}