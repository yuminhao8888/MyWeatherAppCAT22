package com.example.myweatherappcat22.viewmodel

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherappcat22.model.Forecast
import com.example.myweatherappcat22.model.Main
import com.example.myweatherappcat22.rest.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _cityForecast: MutableLiveData<ResultState> = MutableLiveData(ResultState.LOADING)
    val cityForecast: LiveData<ResultState> get() = _cityForecast

    var cityName = "Atlanta"
    var forecast : Forecast? = null

    private val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentDate = sdf.format(Date())

    fun getForecast() {

        //Log.d("cityname", this.cityName)

        // This is not needed you can set the default value to Atlanta
        //val cityame = if(this.cityName == "") "Atlanta" else this.cityName

        viewModelScope.launch(ioDispatcher) {
            // here is the worker thread
            try {
                val response = weatherRepository.getCityForecast(cityName)
                if (response.isSuccessful) {
                    response.body()?.let {
                        // here i have my forecast for the city
                        // _cityForecast.postValue(ResultState.SUCCESS(it))
                        withContext(Dispatchers.Main) {
                            // here I am in the MAIN thread
                            _cityForecast.value = ResultState.SUCCESS(it)
                        }
                    } ?: throw Exception("Response null")
                } else {
                    throw Exception("No success")
                }
            } catch (e: Exception) {
               _cityForecast.postValue(ResultState.ERROR(e))
            }
        }
    }
}