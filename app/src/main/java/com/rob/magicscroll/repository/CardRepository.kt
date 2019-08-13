package com.rob.magicscroll.repository

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import com.rob.magicscroll.model.CardDao
import com.rob.magicscroll.model.entities.CardEntity
import com.rob.magicscroll.remote.CardApi
import com.rob.magicscroll.model.entities.CardEntityList
import com.rob.magicscroll.model.entities.MockCardEntity

class CardRepository(private val cardApi: CardApi, private val cardDao: CardDao) {


//    val shared = CardRepository(cardApi, cardDao)
    var cardList: CardEntityList = CardEntityList()
    var cardListObservable: Observable<CardEntityList> = Observable.empty()
    init{
        this.makeDummyCardList()
    }

    fun makeDummyCardList() {
        val cardNames = mutableListOf("card 1", "card 2", "card 3", "card 4", "card 5", "card 6",
            "card 7", "card8", "card 9", "card 10")

        var count = 1
        for (cardName in cardNames) {
            val newCard = CardEntity()
            newCard.id = count.toString()
            newCard.name = cardName

            cardList.cardEntities = cardList.cardEntities?.plusElement(newCard)
            count++
        }
        cardListObservable = Observable.fromArray(cardList)
    }

    fun getAllCards() : Observable<CardEntityList> {
        return cardListObservable
    }

    fun getCard(id: Int) : CardEntity? {
        for (card in cardList.cardEntities!!) {
            if(card.id.toInt() == id) {
                return card
            }
        }
        return null
    }

    fun getCards(): Observable<CardEntityList> {
        return Observable.concatArray(
            getCardsFromDb(),
            getCardsFromApi()
        )
    }

    fun countCards(): Int {
        return cardDao.countCards()
    }

    fun getCardsFromDb(): Observable<CardEntityList> {
        return cardDao.getAllCards().filter { it != null }.toObservable()
    }

    fun getCardsFromApi(): Observable<CardEntityList> {
        return cardApi.getCards().doOnNext { storeCardsInDb(it) }
    }

    fun storeCardsInDb(cards: CardEntityList) {
        Observable.fromCallable { cardDao.insertAllCards(cards) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }


}