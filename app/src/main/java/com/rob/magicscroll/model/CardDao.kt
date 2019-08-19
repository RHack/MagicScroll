package com.rob.magicscroll.model

import androidx.room.*
import com.rob.magicscroll.model.entities.CardEntity
import com.rob.magicscroll.model.entities.CardEntityList
import io.reactivex.Single

@Dao
interface CardDao {
    @Query("SELECT * FROM cardList")
    fun getCards(): Single<CardEntityList>

    @Query("SELECT COUNT(cardEntities) FROM cardList")
    fun countCards() : Int

    @Query("SELECT * FROM cards WHERE name LIKE :cardName")
    fun findCardByName(cardName: String): CardEntity

    @Insert
    fun insertAllCards(cards: CardEntityList)

    @Delete
    fun deleteCard(card: CardEntity)

    @Update
    fun updateTodo(vararg cards: CardEntity)
}