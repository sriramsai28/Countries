package com.walmart.sriram.countries.data.repositoryimpl

import com.walmart.sriram.countries.data.model.CountriesResponse
import com.walmart.sriram.countries.data.repositoryimpl.datasource.CountriesRemoteDataSource
import com.walmart.sriram.countries.data.repositoryimpl.datasourceImpl.CountriesCacheDataSourceImpl
import com.walmart.sriram.countries.data.repositoryimpl.datasourceImpl.CountriesRemoteDataSourceImpl
import com.walmart.sriram.countries.domain.repository.CountriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountriesRepositoryImpl(
    private val remote: CountriesRemoteDataSource = CountriesRemoteDataSourceImpl(),
) : CountriesRepository {

    override suspend fun getCountries(): Flow<CountriesResponse> = flow {
        if (CountriesCacheDataSourceImpl.getCountriesFromCache().isEmpty()) {
            try {
                // handle response here.
                val response = remote.getCountriesFromAPI()
                if (response.isSuccessful) {
                    // Handle the list response.
                    response.body()?.let {
                        CountriesCacheDataSourceImpl.saveCountriesToCache(it)
                        emit(CountriesResponse(it, null))
                        return@flow
                    }
                } else {
                    // handle failure.
                    emit(
                        CountriesResponse(
                            null,
                            response.errorBody()?.string()
                                ?: "Opps! Failed to fetch data."
                        )
                    )
                }
            } catch (e: Exception) {
                // handel exception
                emit(
                    CountriesResponse(
                        null,
                        e.message
                            ?: "Something went wrong.Try again!"
                    )
                )
            }
        } else {
            emit(
                CountriesResponse(
                    CountriesCacheDataSourceImpl.getCountriesFromCache(),
                    null
                )
            )
        }
    }
}
