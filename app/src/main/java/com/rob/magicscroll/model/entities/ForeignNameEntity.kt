package com.rob.magicscroll.model.entities


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "foreignNames")
class ForeignNameEntity {
    @SerializedName("name")
    var name: String? = null
    @SerializedName("language")
    var language: String? = null
    @SerializedName("multiverseid")
    var multiverseid: Int? = null
}