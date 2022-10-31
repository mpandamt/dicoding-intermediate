package com.example.mystoryappdicoding.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mystoryappdicoding.data.remote.ApiService
import com.example.mystoryappdicoding.model.UserPreference
import com.example.mystoryappdicoding.view.login.LoginViewModel
import com.example.mystoryappdicoding.view.main.MainViewModel
import com.example.mystoryappdicoding.view.maps.MapsViewModel
import com.example.mystoryappdicoding.view.signup.SignupViewModel

class ViewModelFactory(private val pref: UserPreference, private val apiService: ApiService) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(pref, apiService) as T
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(pref, apiService) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref, apiService) as T
            }
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
                MapsViewModel(apiService) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}