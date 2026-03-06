package com.example.olchatr

import io.ktor.client.HttpClient

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect val baseUrl: String

expect fun createHttpClient(): HttpClient