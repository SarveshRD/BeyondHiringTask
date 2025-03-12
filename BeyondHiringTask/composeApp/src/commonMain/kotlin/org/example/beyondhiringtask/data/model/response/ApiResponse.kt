package org.example.beyondhiringtask.data.model.response


import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val status: Int,
    val message: String,
    val data: ProductData
)