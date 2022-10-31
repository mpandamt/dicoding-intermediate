package com.example.mystoryappdicoding.view.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystoryappdicoding.data.remote.ApiService
import com.example.mystoryappdicoding.data.remote.dto.LoginResponse
import com.example.mystoryappdicoding.data.remote.dto.RegisterRequest
import com.example.mystoryappdicoding.data.remote.dto.RegisterResponse
import com.example.mystoryappdicoding.model.ApiResult
import com.example.mystoryappdicoding.model.UserModel
import com.example.mystoryappdicoding.model.UserPreference
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel(private val pref: UserPreference, private val apiService: ApiService) : ViewModel() {
    private val _registerResult = MutableLiveData<ApiResult>()
    val registerResult = _registerResult

    fun register(name: String, email: String, password: String) {
        _registerResult.value = ApiResult(isLoading = true, success = false)
        apiService.register(RegisterRequest(name, email, password)).enqueue(
            object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        _registerResult.value = ApiResult(isLoading = false, success = true, message = response.body()?.message.orEmpty())
                    } else {
                        val type = object : TypeToken<LoginResponse>() {}.type
                        val errorResponse: LoginResponse? = Gson().fromJson(response.errorBody()!!.charStream(), type)
                        _registerResult.value = ApiResult(isLoading = false, success= false, message = errorResponse?.message.orEmpty())
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    _registerResult.value = ApiResult(
                        isLoading = true, success = false, message = t.message.toString()
                    )
                }

            }
        )
    }
}