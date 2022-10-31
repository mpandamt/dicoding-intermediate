package com.example.mystoryappdicoding.view.login

import android.util.Log
import androidx.lifecycle.*
import com.example.mystoryappdicoding.data.remote.ApiService
import com.example.mystoryappdicoding.data.remote.dto.LoginRequest
import com.example.mystoryappdicoding.data.remote.dto.LoginResponse
import com.example.mystoryappdicoding.model.ApiResult
import com.example.mystoryappdicoding.model.UserModel
import com.example.mystoryappdicoding.model.UserPreference
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val pref: UserPreference, private val apiService: ApiService) : ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    private val _loginResult = MutableLiveData<ApiResult>()
    val loginResult = _loginResult

    fun login(email: String, password: String) {
        _loginResult.value = ApiResult(isLoading = true)
        apiService.login(LoginRequest(email = email, password = password)).enqueue(object: Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.error == false) {
                        viewModelScope.launch {
                            pref.saveUser(UserModel(
                                name = response.body()?.loginResult?.name.orEmpty(),
                                email = email,
                                password = password,
                                token = response.body()?.loginResult?.token.orEmpty(),
                                userId = response.body()?.loginResult?.userId.orEmpty(),
                                isLogin = true
                            ))
                        }
                    }
                    _loginResult.value = ApiResult(isLoading = false, message = response.body()?.message.orEmpty())

                } else {
                    val type = object : TypeToken<LoginResponse>() {}.type
                    val errorResponse: LoginResponse? = Gson().fromJson(response.errorBody()!!.charStream(), type)
                    _loginResult.value = ApiResult(isLoading = false, message = errorResponse?.message.orEmpty())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("fail", t.message.toString())

                _loginResult.value = ApiResult(isLoading = false, message = t.message.orEmpty())
            }
        })
    }
}