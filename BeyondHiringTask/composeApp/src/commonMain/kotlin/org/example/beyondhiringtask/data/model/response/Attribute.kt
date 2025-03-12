package org.example.beyondhiringtask.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attribute(
    @SerialName("attribute_image_url")
    val attributeImageUrl: String,
    @SerialName("color_code")
    val colorCode: String,
    @SerialName("images")
    val images: List<String>,
    @SerialName("option_id")
    val optionId: String,
    @SerialName("price")
    val price: String,
    @SerialName("swatch_url")
    val swatchUrl: String,
    @SerialName("value")
    val value: String
)