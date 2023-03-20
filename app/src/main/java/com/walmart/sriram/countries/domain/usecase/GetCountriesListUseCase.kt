package com.walmart.sriram.countries.domain.usecase

import com.walmart.sriram.countries.data.model.CountriesResponse
import com.walmart.sriram.countries.data.repositoryimpl.CountriesRepositoryImpl
import kotlinx.coroutines.flow.Flow

class GetCountriesListUseCase {
    suspend fun execute(): Flow<CountriesResponse> = CountriesRepositoryImpl().getCountries()
}
