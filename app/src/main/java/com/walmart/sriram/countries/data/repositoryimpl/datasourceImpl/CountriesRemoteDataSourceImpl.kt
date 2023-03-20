package com.walmart.sriram.countries.data.repositoryimpl.datasourceImpl

import com.walmart.sriram.countries.data.api.RetrofitInstance
import com.walmart.sriram.countries.data.model.Countries
import com.walmart.sriram.countries.data.repositoryimpl.datasource.CountriesRemoteDataSource
import retrofit2.Response

class CountriesRemoteDataSourceImpl : CountriesRemoteDataSource {

    override suspend fun getCountriesFromAPI(): Response<Countries> {
        // fetch data from API.
        return RetrofitInstance.countriesService.getCountries()
    }
}
