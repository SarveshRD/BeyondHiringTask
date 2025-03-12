package org.example.beyondhiringtask

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform