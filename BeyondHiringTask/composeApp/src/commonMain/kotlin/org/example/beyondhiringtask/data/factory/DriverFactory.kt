package org.example.beyondhiringtask.data.factory

import io.ktor.client.HttpClient

expect class ApiService() {
    fun build(): HttpClient
}


