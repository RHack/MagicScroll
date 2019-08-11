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
    var cardList: List<MockCardEntity> = emptyList()
    init{
        this.makeDummyCardList()
    }

    fun makeDummyCardList() {
        val cardNames = mutableListOf("card 1", "card 2", "card 3")

        var count = 1
        for (cardName in cardNames) {
            val newCard = MockCardEntity(count, cardName)

            cardList += newCard
            count++
        }
    }

    fun getAllCards() : List<MockCardEntity> {
        return cardList
    }

    fun getCard(id: Int) : MockCardEntity? {
        for (card in cardList) {
            if(card.id == id) {
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

//// so pretend that's what u got.  every other properties are just optional
//class CardEntity{
//    var id: Int = "" // Make this a numeric?
//    var name: String? = null
//
//    public CardEntity (Int id, String name) {
//        self.id = id
//        self.name = name
//    }
//}
//
//// now in your repository
//class CardRepository {
//    // no inheritence/injection for now
//    let cardList: [CardEntity] = []
//
//    init() {
//        self.cardList = self.makeDummyCardList()
//    }
//
//    func makeDummyCardList() -> [CardEntity] {
//        let cardNames: [String] = ["Card 1",
//            "Card 2",
//            "Card 3"]
//        var cardList: [CardEntity] = []
//        var count = 1
//        for cardName in cardNames {
//            let newCard = CardEntity(id: count, name: cardName)
//            cardList.append(newCard)
//            count += 1
//        }
//
//        return cardList
//    }
//
//    func getAllCards() -> [cardList] {
//        return self.cardList
//    }
//
//    func getCard(id: Int) -> CArdEntity {
//        for card in self.cardList
//    }
//}