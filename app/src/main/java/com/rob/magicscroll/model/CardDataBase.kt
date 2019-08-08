package com.rob.magicscroll.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rob.magicscroll.model.entities.CardEntity
import com.rob.magicscroll.model.entities.CardEntityList


@Database(entities = [CardEntity::class, CardEntityList::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class CardDataBase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}