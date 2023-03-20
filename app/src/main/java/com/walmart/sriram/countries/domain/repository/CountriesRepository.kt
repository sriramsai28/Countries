package com.walmart.sriram.countries.domain.repository

import com.walmart.sriram.countries.data.model.CountriesResponse
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    suspend fun getCountries(): Flow<CountriesResponse>
}