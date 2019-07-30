package com.rob.magicscroll.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_items")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "cardName") var cardName: String,
    @ColumnInfo(name = "cardManaCost") var cardManaCost: String,
    @ColumnInfo(name = "cardImageUrl") var cardImageUrl: String,
    @ColumnInfo(name = "cardOracleText") var cardOracleText: String
)