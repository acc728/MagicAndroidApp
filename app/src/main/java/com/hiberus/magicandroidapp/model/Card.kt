package com.hiberus.magicandroidapp.model

import androidx.annotation.Keep
@Keep
data class Card (
    val welcomeObject: String,
    val id: String,
    val oracleID: String,
    val multiverseIDS: List<Long>,
    val tcgplayerID: Long,
    val cardmarketID: Long,
    val name: String,
    val lang: String,
    val releasedAt: String,
    val uri: String,
    val scryfallURI: String,
    val layout: String,
    val highresImage: Boolean,
    val imageStatus: String,
    val imageUris: ImageUris,
    val manaCost: String,
    val cmc: Long,
    val typeLine: String,
    val oracleText: String,
    val colors: List<String>,
    val colorIdentity: List<String>,
    val keywords: List<Any?>,
    val legalities: Legalities,
    val games: List<String>,
    val reserved: Boolean,
    val foil: Boolean,
    val nonfoil: Boolean,
    val finishes: List<String>,
    val oversized: Boolean,
    val promo: Boolean,
    val reprint: Boolean,
    val variation: Boolean,
    val setID: String,
    val set: String,
    val setName: String,
    val setType: String,
    val setURI: String,
    val setSearchURI: String,
    val scryfallSetURI: String,
    val rulingsURI: String,
    val printsSearchURI: String,
    val collectorNumber: String,
    val digital: Boolean,
    val rarity: String,
    val cardBackID: String,
    val artist: String,
    val artistIDS: List<String>,
    val illustrationID: String,
    val borderColor: String,
    val frame: String,
    val securityStamp: String,
    val fullArt: Boolean,
    val textless: Boolean,
    val booster: Boolean,
    val storySpotlight: Boolean,
    val edhrecRank: Long,
    val pennyRank: Long,
    val prices: Prices,
    val relatedUris: RelatedUris,
    val purchaseUris: PurchaseUris
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
data class Legalities (
    val standard: String,
    val future: String,
    val historic: String,
    val gladiator: String,
    val pioneer: String,
    val explorer: String,
    val modern: String,
    val legacy: String,
    val pauper: String,
    val vintage: String,
    val penny: String,
    val commander: String,
    val oathbreaker: String,
    val brawl: String,
    val historicbrawl: String,
    val alchemy: String,
    val paupercommander: String,
    val duel: String,
    val oldschool: String,
    val premodern: String,
    val predh: String
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
data class RelatedUris (
    val gatherer: String,
    val tcgplayerInfiniteArticles: String,
    val tcgplayerInfiniteDecks: String,
    val edhrec: String
)
