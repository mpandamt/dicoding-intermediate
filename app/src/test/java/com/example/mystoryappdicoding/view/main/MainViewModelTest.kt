package com.example.mystoryappdicoding.view.main

import android.preference.Preference
import androidx.datastore.core.DataStore
import com.example.mystoryappdicoding.model.UserPreference
import com.example.mystoryappdicoding.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest{
    @Mock
    private lateinit var mainViewModel: MainViewModel
    @Mock
    private lateinit var userPreference: UserPreference
    private val dummyStory = DataDummy.generateDummyStory()

    @Before
    fun setUp() {

    }
}