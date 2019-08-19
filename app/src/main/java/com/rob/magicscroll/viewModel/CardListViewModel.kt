package com.rob.magicscroll.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rob.magicscroll.App
import com.rob.magicscroll.model.entities.CardEntity
import io.reactivex.Observable
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
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
            CardList(it.cardEntities.take(20), "20 cards")
        }.onErrorReturn {
            CardList(emptyList(), "ERROR", it)
        }
    }
}