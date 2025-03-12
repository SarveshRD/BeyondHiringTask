package org.example.beyondhiringtask.di

import org.koin.dsl.module
import presentation.viewModel.AnesthesiaDetailViewModel


val viewModelModule = module {

    single { AnesthesiaDetailViewModel(get()) }

}