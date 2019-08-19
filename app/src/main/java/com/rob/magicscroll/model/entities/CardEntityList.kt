package com.rob.magicscroll.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cardList")
data class CardEntityList(
    @PrimaryKey
    @SerializedName("cards")
    var cardEntities: List<CardEntity>
)