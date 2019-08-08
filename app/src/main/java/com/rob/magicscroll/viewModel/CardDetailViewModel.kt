package com.rob.magicscroll.viewModel

import com.rob.magicscroll.model.entities.CardEntity

data class CardList(val cards: List<CardEntity>, val message: String, val error: Throwable? = null)