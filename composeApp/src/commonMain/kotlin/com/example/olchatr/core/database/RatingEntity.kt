package com.example.olchatr.core.database

import kotlinx.serialization.Serializable

@Serializable
data class RatingEntity (
  val rate: Double? = null,
  val count: Int? = null
)