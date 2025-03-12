package org.example.beyondhiringtask.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    @SerialName("rating_count")
    val ratingCount: Int,
    @SerialName("total_review")
    val totalReview: Int
)