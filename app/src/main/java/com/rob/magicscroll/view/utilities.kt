package com.rob.magicscroll.view

import android.widget.ImageView
import com.rob.magicscroll.R
import com.squareup.picasso.Picasso

val CARD_SIZE = 100
val DETAIL_CARD_SIZE = 700
val CARD_NAME = "card_name"
val CARD_IMAGE = "card_image"
val CARD_MANA_COST = "card_mana_cost"
val CARD_TEXT = "card_text"
val NULL_STATE = null
val RECYCLER_POSITION = "recycler_position"

private val NULL_STRING = "null"
private val NULL_VALUE = null
private val HEIGHT_MULTIPLIER = 2
private val HTTP_STRING = "http"
private val HTTPS_STRING = "https"
private val SUBSTRING_SIZE = 5

// Correct cards' image urls to be properly displayed
fun convertToHttps(imageUrl: String?): String? {
    return when {
        imageUrl == NULL_STRING -> NULL_VALUE
        imageUrl?.substring(SUBSTRING_SIZE) == HTTPS_STRING -> imageUrl
        else -> imageUrl?.replace(HTTP_STRING, HTTPS_STRING)
    }
}

// Load images from their url into the given imageView
fun loadImage(cardImage: ImageView, imageUrl: String?, size: Int) {
    Picasso.get()
        .load(convertToHttps(imageUrl))
        .resize(size, size* HEIGHT_MULTIPLIER)
        .centerInside()
        .placeholder(R.drawable.default_card)
        .into(cardImage)
}