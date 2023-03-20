package com.walmart.sriram.countries.ui.activity

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.walmart.sriram.countries.R
import com.walmart.sriram.countries.databinding.ActivityMainBinding
import com.walmart.sriram.countries.ui.adapter.CountryListAdapter
import com.walmart.sriram.countries.ui.viewmodel.CountriesViewModel

/**
 * Activity to display Recycler view with list of countries.
 * */
class MainActivity : AppCompatActivity() {

    // data binding variable.
    private lateinit var binding: ActivityMainBinding

    // view model instance.
    private val countriesViewModel by viewModels<CountriesViewModel>()

    // recycler view Adapter
    private lateinit var countryListAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeRecyclerViewAdapter()
        setObservers()
    }

    /**
     * Setup recyclerview.
     * */
    private fun initializeRecyclerViewAdapter() {
        countryListAdapter = CountryListAdapter()
        binding.countriesList.adapter = countryListAdapter
        binding.countriesList.layoutManager = LinearLayoutManager(this)
        setRecyclerViewContent()
    }

    /**
     * Setup recyclerview data. Makes the API call after checking for network connection.
     * */
    private fun setRecyclerViewContent() {
        if (checkInternet()) {
            countriesViewModel.fetchCountriesList()
        } else {
            countriesViewModel.setNetworkErrorMessage(resources.getString(R.string.no_internet))
        }
    }

    /**
     * Set observes to observe the live data from view model.
     * Updates the view based on live data.
     * */
    private fun setObservers() {
        countriesViewModel.loading.observe(
            this
        ) { isLoading ->
            binding.loadingContainer.visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
        countriesViewModel.countriesList.observe(
            this
        ) { list ->
            binding.loadingContainer.visibility = View.GONE
            countryListAdapter.setList(list)
        }
        countriesViewModel.errorMessageLiveData.observe(
            this
        ) { message ->
            message?.let { showErrorDialog(it) }
        }
    }

    /**
     * Display error to user.
     * */
    private fun showErrorDialog(message: String) {
        val alertDialog = AlertDialog.Builder(this)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.retry) { it, _ ->
                it.dismiss()
                // Fetch data again.
                setRecyclerViewContent()
            }
        alertDialog.show()
    }

    /**
     * Check if device is connected to the internet.
     * */
    fun checkInternet(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        return cm!!.activeNetworkInfo != null && cm!!.activeNetworkInfo!!.isConnected
    }
}
