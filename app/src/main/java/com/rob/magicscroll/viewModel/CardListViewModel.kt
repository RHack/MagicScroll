package com.rob.magicscroll.viewModel

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import com.rob.magicscroll.repository.CardRepository

class CardListViewModel(private val cardRepository: CardRepository) : ViewModel() {
    // Get 100 cards from the repository
    fun getCards(): Observable<CardList> {
        return cardRepository.getCards().map {
            CardList(it.cardEntities.take(100), "Successfully retrieved 100 cards from the repository")
        }.onErrorReturn {
            CardList(emptyList(), "ERROR: getCards() failed, ", it)
        }
    }
}