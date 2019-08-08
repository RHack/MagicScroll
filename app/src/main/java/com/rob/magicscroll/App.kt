package com.rob.magicscroll

import android.app.Application
import androidx.room.Room
import com.rob.magicscroll.model.CardDataBase
import com.rob.magicscroll.remote.CardApi
import com.rob.magicscroll.repository.CardRepository
import com.rob.magicscroll.viewModel.CardListViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    companion object {
        private lateinit var retrofit: Retrofit
        private lateinit var cardApi: CardApi
        private lateinit var cardRepository: CardRepository
        private lateinit var cardListViewModel: CardListViewModel
        private lateinit var cardDataBase: CardDataBase

        fun injectCardApi() = cardApi
        fun injectCardListViewModel() = cardListViewModel
        fun injectCardDao() = cardDataBase.cardDao()
    }

    override fun onCreate() {
        super.onCreate()
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://api.magicthegathering.io/v1/")
            .build()

        cardApi = retrofit.create(CardApi::class.java)
        cardDataBase = Room.databaseBuilder(applicationContext, CardDataBase::class.java, "card-database")
            .build()

        cardRepository = CardRepository(cardApi, cardDataBase.cardDao())
        cardListViewModel = CardListViewModel(cardRepository)
    }
}