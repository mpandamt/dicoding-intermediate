package com.example.mystoryappdicoding.data.remote

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.mystoryappdicoding.model.UserPreference
import okhttp3.Interceptor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Response


class AuthInterceptor constructor(
    private val dataStore: UserPreference
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newBuilder = originalRequest.newBuilder()
        var token: String
        runBlocking {
            token = dataStore.getToken().first()
        }
        if (token.isNotEmpty()){
            newBuilder.addHeader("Authorization", "Bearer $token")

        }
        val request = newBuilder.build()
        return chain.proceed(request = request)
    }
}
