package com.walmart.sriram.countries.data.repositoryimpl.datasourceImpl

import com.walmart.sriram.countries.data.model.CountriesItem
import com.walmart.sriram.countries.data.repositoryimpl.datasource.CountriesCacheDataSource

object CountriesCacheDataSourceImpl : CountriesCacheDataSource {
    private var countriesList = ArrayList<CountriesItem>()
    override suspend fun getCountriesFromCache(): List<CountriesItem> {
        return countriesList
    }

    override suspend fun saveCountriesToCache(countries: List<CountriesItem>) {
        countriesList.clear()
        countriesList = ArrayList(countries)
    }
}
