package org.example.beyondhiringtask.di

import org.example.beyondhiringtask.data.factory.ApiService
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {

    single { ApiService().build() }

}