package org.example.beyondhiringtask.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow

class AnesthesiaDetailRepository(private val httpClient: HttpClient) {

    fun getData(): Flow<NetWorkResult<Any?>> {
        return toResultFlow {

            val response =
                httpClient.get("https://klinq.com/rest/V1/productdetails/6701/253620?lang=en&store=KWD") {
                    contentType(ContentType.Application.Json)
                }.body<String>()
            NetWorkResult.Success(response)
        }
    }
}