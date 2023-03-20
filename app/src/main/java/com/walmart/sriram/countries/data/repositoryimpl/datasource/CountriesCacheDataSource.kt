package com.walmart.sriram.countries.data.repositoryimpl.datasource

import com.walmart.sriram.countries.data.model.CountriesItem

interface CountriesCacheDataSource {
    suspend fun getCountriesFromCache(): List<CountriesItem>

    suspend fun saveCountriesToCache(countries: List<CountriesItem>)
}
