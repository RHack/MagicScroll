package com.rob.magicscroll.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cards")
data class CardEntity(
    @SerializedName("name")
    val name: String,
//    @SerializedName("names")
//    var names: List<String>? = null
//    @SerializedName("manaCost")
//    var manaCost: String? = null
//    @SerializedName("cmc")
//    var cmc: Int? = null
//    @SerializedName("colors")
//    var colors: List<String>? = null
//    @SerializedName("colorIdentity")
//    var colorIdentity: List<String>? = null
//    @SerializedName("type")
//    var type: String? = null
//    @SerializedName("supertypes")
//    var supertypes: List<String>? = null
//    @SerializedName("types")
//    var types: List<String>? = null
//    @SerializedName("subtypes")
//    var subtypes: List<String>? = null
//    @SerializedName("rarity")
//    var rarity: String? = null
//    @SerializedName("set")
//    var set: String? = null
//    @SerializedName("text")
//    var text: String? = null
//    @SerializedName("artist")
//    var artist: String? = null
//    @SerializedName("number")
//    var number: String? = null
//    @SerializedName("power")
//    var power: String? = null
//    @SerializedName("toughness")
//    var toughness: String? = null
//    @SerializedName("layout")
//    var layout: String? = null
//    @SerializedName("multiverseid")
//    var multiverseid: Int? = null
//    @SerializedName("imageUrl")
//    var imageUrl: String? = null
//    @SerializedName("rulings")
//    var rulings: List<RulingEntity>? = null
//    @SerializedName("foreignNames")
//    var foreignNames: List<ForeignNameEntity>? = null
//    @SerializedName("printings")
//    var printings: List<String>? = null
//    @SerializedName("originalText")
//    var originalText: String? = null
//    @SerializedName("originalType")
//    var originalType: String? = null
    @PrimaryKey
    @SerializedName("id")
    val id: String
)

