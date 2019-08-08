package com.rob.magicscroll.viewModel

import io.reactivex.Observable
import com.rob.magicscroll.repository.CardRepository

class CardListViewModel(private val cardRepository: CardRepository) {
    fun getCard(): Observable<CardList> {
        return cardRepository.getCards().map {
            CardList(it.cardEntities!!.take(1), "1 card")
        }.onErrorReturn {
            CardList(emptyList(), "ERROR", it)
        }
    }

    fun getCards(): Observable<CardList> {
        return cardRepository.getCards().map {
            CardList(it.cardEntities!!.take(10), "10 cards")
        }.onErrorReturn {
            CardList(emptyList(), "ERROR", it)
        }
    }

    fun countCards(): Int {
        return cardRepository.countCards()
    }
}