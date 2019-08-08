package com.rob.magicscroll.model.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "foreignNames")
class RulingEntity {

    @SerializedName("date")
    var date: String? = null
    @SerializedName("text")
    var text: String? = null
}