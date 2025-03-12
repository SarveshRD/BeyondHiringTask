package org.example.beyondhiringtask.di


import org.example.beyondhiringtask.data.repository.AnesthesiaDetailRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<AnesthesiaDetailRepository> { AnesthesiaDetailRepository(get()) }


}