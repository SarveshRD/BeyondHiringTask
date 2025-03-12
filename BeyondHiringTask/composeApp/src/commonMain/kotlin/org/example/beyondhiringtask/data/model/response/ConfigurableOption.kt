package org.example.beyondhiringtask.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigurableOption(
    @SerialName("attribute_code")
    val attributeCode: String,
    @SerialName("attribute_id")
    val attributeId: Int,
    @SerialName("attributes")
    val attributes: List<Attribute>,
    @SerialName("type")
    val type: String
)