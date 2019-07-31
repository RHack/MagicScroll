package com.rob.magicscroll.remote

import io.reactivex.Observable
import com.rob.magicscroll.model.CardEntity
import retrofit2.http.GET

interface CardApi {
    @GET("https://api.magicthegathering.io/v1/cards")
    fun getCards(): Observable<List<CardEntity>>
}