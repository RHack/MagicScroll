package com.rob.magicscroll.model

import androidx.room.*

@Dao
interface CardDao {
    @Query("SELECT * FROM card_items")
    fun getAll(): List<CardEntity>

    @Query("SELECT * FROM card_items WHERE cardName LIKE :cardName")
    fun findByCardName(cardName: String): CardEntity

    @Insert
    fun insertAll(vararg card: CardEntity)

    @Delete
    fun delete(card: CardEntity)

    @Update
    fun updateTodo(vararg cards: CardEntity)
}