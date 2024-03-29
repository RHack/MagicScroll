package com.rob.magicscroll.repository

import android.util.Log
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import com.rob.magicscroll.model.CardDao
import com.rob.magicscroll.model.entities.CardEntity
import com.rob.magicscroll.remote.CardApi
import com.rob.magicscroll.model.entities.CardEntityList

class CardRepository(private val cardApi: CardApi, private val cardDao: CardDao) {
    var cardList: CardEntityList = CardEntityList(emptyList())
    var cardListObservable: Observable<CardEntityList> = Observable.empty()

    init {
        this.makeDummyCardList()
    }

    // Creates a list of dummy cards for testing
    private fun makeDummyCardList() {
        val cardNames = mutableListOf(
            "card 1", "card 2", "card 3", "card 4", "card 5", "card 6",
            "card 7", "card8", "card 9", "card 10"
        )
        var count = 1

        for (cardName in cardNames) {
            val newCard = CardEntity(cardName, null, null, null, count.toString())

            cardList.cardEntities = cardList.cardEntities?.plusElement(newCard)
            count++
        }
        cardListObservable = Observable.fromArray(cardList)
    }

    fun getCards(): Observable<CardEntityList> {
        return Observable.concatArray(
//            cardListObservable,
//            getCardsFromDb(),
            getCardsFromApi()
        )
    }

    fun countCards(): Int {
        return cardDao.countCards()
    }

    fun getCardsFromDb(): Observable<CardEntityList> {
        return cardDao.getCards().filter { it != null }.toObservable()
    }

    fun getCardsFromApi(): Observable<CardEntityList> {
        return cardApi.getCards().map { it }
//        return cardApi.getCards().map{it}.doOnNext { storeCardsInDb(it) }
    }

    fun storeCardsInDb(cards: CardEntityList) {
        Observable.fromCallable { cardDao.insertAllCards(cards) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                Log.i("Success", "${cards.cardEntities[0].name}")
            }, {
                Log.e("Error", it.localizedMessage)
            })
    }


}