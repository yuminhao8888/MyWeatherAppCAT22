package com.example.myweatherappcat22.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myweatherappcat22.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

open class BaseFragment : Fragment() {

    public val weatherViewModel: WeatherViewModel by sharedViewModel()
}