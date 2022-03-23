package com.example.myweatherappcat22.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherappcat22.R
import com.example.myweatherappcat22.databinding.ForcastItemBinding
import com.example.myweatherappcat22.model.Forecast
import com.example.myweatherappcat22.model.Main
import com.example.myweatherappcat22.viewmodel.WeatherViewModel
import com.example.myweatherappcat22.views.BaseFragment
import com.example.myweatherappcat22.views.ForecastFragment

class WeatherAdapter(
    private val forecastList: MutableList<Forecast> = mutableListOf(),
    private val onForecastClicked: (Forecast) -> Unit
) : RecyclerView.Adapter<ForecastViewHolder>() {

    fun setForecast(newForecast: List<Forecast>) {
        forecastList.clear()
        forecastList.addAll(newForecast)
        notifyItemRangeChanged(0, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = ForcastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(view, onForecastClicked)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) =
        holder.bind(forecastList[position])

    override fun getItemCount(): Int = forecastList.size
}

class ForecastViewHolder(
    private val binding: ForcastItemBinding,
    private val onForecastClicked: (Forecast) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(forecast: Forecast) {
        binding.weatherTemp.text = "Temperature: " + forecast.main.temp.toString() + " C"
        binding.textViewHumidity.text = "Humidity: " + forecast.main.humidity.toString()
        binding.textViewPressure.text = "Pressure: " + forecast.main.pressure.toString()

        binding.cardView.setOnClickListener{

            onForecastClicked.invoke(forecast)
            //Log.d("cityname", weatherViewModel.getCityName())

//            findNavController().navigate(R.id.action_SearchFragment_to_ForecastFragment)
        }
        //binding.mRating.setProgress(2)
    }

}