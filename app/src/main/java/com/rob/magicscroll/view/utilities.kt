package com.rob.magicscroll.view

import android.widget.ImageView
import com.rob.magicscroll.R
import com.squareup.picasso.Picasso

fun convertToHttps(imageUrl: String?) : String? {
    return if (imageUrl?.substring(5) == "https") {
        imageUrl
    } else {
        imageUrl?.replace("http", "https")
    }
}

fun loadImage(cardImage: ImageView, imageUrl: String?) {
    Picasso.get()
        .load(convertToHttps(imageUrl))
        .resize(100, 200)
        .centerInside()
        .placeholder(R.drawable.default_card)
        .into(cardImage)
}