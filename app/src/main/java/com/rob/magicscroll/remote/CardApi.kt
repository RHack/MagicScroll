package com.rob.magicscroll.remote

import com.rob.magicscroll.model.entities.CardEntity
import io.reactivex.Observable
import com.rob.magicscroll.model.entities.CardEntityList
import retrofit2.http.GET

interface CardApi {
    @GET("cards/")
    fun getCards(): Observable<CardEntityList>
}