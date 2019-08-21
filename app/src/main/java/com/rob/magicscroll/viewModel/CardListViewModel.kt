package com.rob.magicscroll.viewModel

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import com.rob.magicscroll.repository.CardRepository

class CardListViewModel(private val cardRepository: CardRepository): ViewModel() {
    fun getCard(): Observable<CardList>? {
        return cardRepository.getCards().map {
            CardList(it.cardEntities.take(1), "1 card")
        }?.onErrorReturn {
            CardList(emptyList(), "ERROR", it)
        }
    }

    fun getCards(): Observable<CardList> {
        return cardRepository.getCards().map {
            CardList(it.cardEntities.take(100), "100 cards")
        }.onErrorReturn {
            CardList(emptyList(), "ERROR", it)
        }
    }
}