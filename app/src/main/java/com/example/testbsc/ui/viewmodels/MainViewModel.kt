package com.example.testbsc.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbsc.datasource.DataSource
import com.example.testbsc.ui.Data
import com.example.testbsc.ui.Loading
import com.example.testbsc.ui.ViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val dataSource: DataSource) : ViewModel() {

    val viewState = MutableLiveData<ViewState>()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            viewState.value = Loading
            delay(1000)
            viewState.value = Data(dataSource.getParty())
        }

    }

}