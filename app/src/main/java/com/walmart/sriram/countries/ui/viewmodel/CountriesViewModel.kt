package com.walmart.sriram.countries.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walmart.sriram.countries.data.model.CountriesItem
import com.walmart.sriram.countries.domain.usecase.GetCountriesListUseCase
import kotlinx.coroutines.launch

/**
 * View model to communicate between countries api and UI.
 * */
class CountriesViewModel(
    val useCase: GetCountriesListUseCase = GetCountriesListUseCase()
) : ViewModel() {

    // Live data for loading state.
    private val _isLoading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _isLoading

    // Live data for Error state.
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessageLiveData: LiveData<String?> = _errorMessage

    // Live data for Success state.
    private val _countriesList = MutableLiveData<List<CountriesItem>>()
    val countriesList: LiveData<List<CountriesItem>> = _countriesList

    fun fetchCountriesList() {
        _isLoading.value = true
        viewModelScope.launch {
            useCase.execute().collect {
                if (!it.countrie.isNullOrEmpty()) {
                    it.countrie.let { list ->
                        _isLoading.value = false
                        _countriesList.value = list
                    }
                } else {
                    _isLoading.value = false
                    _errorMessage.value = it.message
                }
            }
        }
    }

    fun setNetworkErrorMessage(message: String) {
        _isLoading.value = false
        _errorMessage.value = message
    }
}
