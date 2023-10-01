package com.hiberus.magicandroidapp.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Keep
@Entity(tableName = "cards")
data class Card (
    @PrimaryKey val id: String,
    val name: String,
    val lang: String,
    val uri: String,
    @SerializedName("image_uris") val imageUris: ImageUris,  // No funciona correctamente
    val cmc: Long,
    @SerializedName("type_line") val typeLine: String,
    @SerializedName("oracle_text") val oracleText: String, // No funciona correctamente
    val colors: List<String>,
    @SerializedName("set_name") val setName: String,  // No funciona correctamente
    val prices: Prices,
    @SerializedName("purchase_uris") val purchaseUris: PurchaseUris,
    var comments: String = ""
)

@Keep
data class ImageUris (
    val small: String,
    val normal: String,
    val large: String,
    val png: String,
    val artCrop: String,
    val borderCrop: String
)

@Keep
data class Prices (
    val usd: String,
    val usdFoil: Any? = null,
    val usdEtched: Any? = null,
    val eur: String,
    val eurFoil: Any? = null,
    val tix: Any? = null
)

@Keep
data class PurchaseUris (
    val tcgplayer: String,
    val cardmarket: String,
    val cardhoarder: String
)

@Keep
data class AutocompleteCardsResult (
    val welcomeObject: String,
    val totalValues: Long,
    val data: List<String>
)

class Converters {

    @TypeConverter
    fun gsonToImagesUris(json: String?): ImageUris? {
        return Gson().fromJson(json, ImageUris::class.java)
    }

    @TypeConverter
    fun imageUrisToGson(imageUris: ImageUris): String? {
        return Gson().toJson(imageUris)
    }
    @TypeConverter
    fun gsonToPrices(json: String?): Prices? {
        return Gson().fromJson(json, Prices::class.java)
    }

    @TypeConverter
    fun pricesToGson(prices: Prices): String? {
        return Gson().toJson(prices)
    }

    @TypeConverter
    fun gsonToPurchasesUris(json: String?): PurchaseUris? {
        return Gson().fromJson(json, PurchaseUris::class.java)
    }

    @TypeConverter
    fun purchasesUrisToGson(purchaseUris: PurchaseUris): String? {
        return Gson().toJson(purchaseUris)
    }

    @TypeConverter
    fun gsonToListStringColors(json: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun listStringColorsToGson(colors: List<String>): String? {
        return Gson().toJson(colors)
    }
}