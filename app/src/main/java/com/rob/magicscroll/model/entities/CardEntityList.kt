package com.rob.magicscroll.model.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.rob.magicscroll.model.entities.CardEntity

@Entity(tableName = "cardList")
class CardEntityList {
    @PrimaryKey
    @NonNull
    @SerializedName("cardEntities")
    var cardEntities: List<CardEntity>? = null
}