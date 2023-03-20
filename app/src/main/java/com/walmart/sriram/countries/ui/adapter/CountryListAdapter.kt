package com.walmart.sriram.countries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.walmart.sriram.countries.data.model.CountriesItem
import com.walmart.sriram.countries.databinding.CountryBinding

/**
 * Adapter for countries list recycler view.
 * Helps display the data in recycler veiw.
 * */
class CountryListAdapter : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {
    private val countryList = ArrayList<CountriesItem>()

    // Get the list of countries from Activity.
    fun setList(dataSet: List<CountriesItem>) {
        countryList.clear()
        countryList.addAll(dataSet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countryList[position]
        // set data to the view.
        holder.bind(country)
    }

    inner class CountryViewHolder(private val countryBinding: CountryBinding) :
        RecyclerView.ViewHolder(countryBinding.root) {

        fun bind(countryItem: CountriesItem) {
            countryBinding.apply {
                this.country = countryItem
                //
                /*this.countryName.text = if (countryItem.region.isEmpty()) {
                    countryItem.name
                } else {
                    countryItem.name + ", " + countryItem.region
                }*/
            }
        }
    }
}
