package com.example.olchatr.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  val title: String,
  val price: Double,
  val description: String,
  val category: String,
  val image: String,
  val ratingRate: Double = 0.0,
  val ratingCount: Int = 0
)