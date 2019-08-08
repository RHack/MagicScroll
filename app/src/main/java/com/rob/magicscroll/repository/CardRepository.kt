package com.rob.magicscroll.repository

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import com.rob.magicscroll.model.CardDao
import com.rob.magicscroll.remote.CardApi
import com.rob.magicscroll.model.entities.CardEntityList

class CardRepository(private val cardApi: CardApi, private val cardDao: CardDao) {
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