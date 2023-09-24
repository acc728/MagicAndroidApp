package com.hiberus.magicandroidapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.errorprone.annotations.Keep

@Keep
@Entity(tableName = "cards")
data class Card(
    @PrimaryKey var id: Int = -1,
    val name: String,
    val oracleText: String,
    val prices: Map<String, String?>,
    val setName: String,
    val imageUris: ImageUris,
    val colors: List<Color>,
    var comments: String = ""
)

@Keep
enum class Color {
    B, G, U, R, W
}

@Keep
data class ImageUris (
    val small: String,
    val normal: String,
    val large: String,
    val png: String,
    val artCrop: String,
    val borderCrop: String
)
