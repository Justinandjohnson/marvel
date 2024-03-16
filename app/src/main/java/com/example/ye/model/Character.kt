package com.example.ye.model

data class Character(
    val id: String,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail // Ensure you have a Thumbnail class defined
)
