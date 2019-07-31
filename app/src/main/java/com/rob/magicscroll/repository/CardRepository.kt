package com.rob.magicscroll.repository

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import com.rob.magicscroll.model.CardDao
import com.rob.magicscroll.model.CardEntity
import com.rob.magicscroll.remote.CardApi

class CardRepository(val cardApi: CardApi, val cardDao: CardDao) {
    fun getCards(): Observable<List<CardEntity>> {
        return Observable.concatArray(
            getCardsFromDb(),
            getCardsFromApi()
        )
    }

    fun getCardsFromDb(): Observable<List<CardEntity>> {
        return cardDao.getAllCards().filter { it.isNotEmpty() }.toObservable()
    }

    fun getCardsFromApi(): Observable<List<CardEntity>> {
        return cardApi.getCards().doOnNext { storeCardsInDb(it) }
    }

    fun storeCardsInDb(cards: List<CardEntity>) {
        Observable.fromCallable { cardDao.insertAllCards(cards) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }


}