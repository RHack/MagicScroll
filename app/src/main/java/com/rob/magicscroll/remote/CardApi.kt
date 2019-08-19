package com.rob.magicscroll.remote

import io.reactivex.Observable
import com.rob.magicscroll.model.entities.CardEntityList
import retrofit2.http.GET

interface CardApi {
    @GET("/v1/cards")
    fun getCards(): Observable<CardEntityList>
}