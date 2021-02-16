package com.example.testbsc.app.koin

import com.example.testbsc.datasource.DataSource
import com.example.testbsc.datasource.LocaleDataSource
import com.example.testbsc.ui.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<DataSource> { LocaleDataSource(get()) }
}

val viewModelsModule = module {
    viewModel{ MainViewModel(get())}
}