package com.rob.magicscroll.model

import androidx.room.TypeConverter
import com.rob.magicscroll.model.entities.CardEntity
import com.rob.magicscroll.model.entities.ForeignNameEntity
import com.rob.magicscroll.model.entities.RulingEntity

class ListConverter {
    @TypeConverter
    fun convertListToString(values: List<String>?): String? {
        return values?.joinToString()
    }

    @TypeConverter
    fun convertStringToList(values: String?): List<String>? {
        return values?.split(", ")
    }

    @TypeConverter
    fun convertForeignNameEntityToString(values: List<ForeignNameEntity>): String? {
        return values?.joinToString()
    }

    @TypeConverter
    fun convertStringToForeignNameEntity(values: String?): List<ForeignNameEntity> {
        return values?.split(", ") as List<ForeignNameEntity>
    }

    @TypeConverter
    fun convertRulingEntityToString(values: List<RulingEntity>): String? {
        return values?.joinToString()
    }

    @TypeConverter
    fun convertStringToRulingEntity(values: String?): List<RulingEntity> {
        return values?.split(", ") as List<RulingEntity>
    }

    @TypeConverter
    fun convertCardEntityToString(values: List<CardEntity>?): String? {
        return values?.joinToString()
    }

    @TypeConverter
    fun convertStringToCartEntity(values: String?): List<CardEntity>? {
        return values?.split(", ") as List<CardEntity>
    }
}