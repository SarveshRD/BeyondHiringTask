package org.example.beyondhiringtask.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductData(
    val id: String,
    val sku: String,
    val name: String,
    @SerialName("price")
    val price: String,
    @SerialName("final_price")
    val finalPrice: String,
    @SerialName("status")
    val status: String,
    @SerialName("type")
    val type: String,
    @SerialName("web_url")
    val webUrl: String,
    @SerialName("brand_name")
    val brandName: String,
    @SerialName("description")
    val description: String,
    @SerialName("image")
    val imageUrl: String
)