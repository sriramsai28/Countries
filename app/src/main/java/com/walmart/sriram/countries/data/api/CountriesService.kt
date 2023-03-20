package com.walmart.sriram.countries.data.api

import com.walmart.sriram.countries.data.model.Countries
import retrofit2.Response
import retrofit2.http.GET

/**
 * A retrofit service to interact with remote API.
 * */
interface CountriesService {

    /**
     * Function to get a list of countries from API.
     *
     * @return: retrofit response containing list of countries.
     * */
    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(): Response<Countries>
}
