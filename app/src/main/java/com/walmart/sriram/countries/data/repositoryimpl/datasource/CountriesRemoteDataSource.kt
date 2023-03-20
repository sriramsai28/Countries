package com.walmart.sriram.countries.data.repositoryimpl.datasource

import com.walmart.sriram.countries.data.model.Countries
import retrofit2.Response

/**
 * Data source to interact with the retrofit service. This interface is implemented in CountriesRemoteDataSourceImpl.
 * */
interface CountriesRemoteDataSource {
    /**
     * a suspend function interacting with countries API service.
     *
     * @return: returns a retrofit response of countries.
     * */
    suspend fun getCountriesFromAPI(): Response<Countries>
}
