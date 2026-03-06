package com.example.olchatr.core.network

import kotlinx.serialization.Serializable

@Serializable
data class RatingDto (
  val rate: Double? = null,
  val count: Int? = null
)