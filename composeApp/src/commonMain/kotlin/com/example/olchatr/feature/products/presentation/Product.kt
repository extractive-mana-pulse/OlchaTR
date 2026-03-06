package com.example.olchatr.feature.products.presentation

data class Product (
  val id: Int,
  val title: String,
  val price: Double,
  val description: String,
  val category: String,
  val image: String,
  val ratingRate: Double = 0.0,
  val ratingCount: Int = 0
)