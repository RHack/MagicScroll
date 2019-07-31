package com.rob.magicscroll.model

import androidx.room.*
import io.reactivex.Single

@Dao
interface CardDao {
    @Query("SELECT * FROM card_items")
    fun getAllCards(): Single<List<CardEntity>>

    @Query("SELECT * FROM card_items WHERE cardName LIKE :cardName")
    fun findCardByName(cardName: String): CardEntity

    @Insert
    fun insertAllCards(vararg cards: List<CardEntity>)

    @Delete
    fun deleteCard(card: CardEntity)

    @Update
    fun updateTodo(vararg cards: CardEntity)
}