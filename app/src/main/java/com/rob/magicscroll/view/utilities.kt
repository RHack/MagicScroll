package com.rob.magicscroll.view

import android.widget.ImageView
import com.rob.magicscroll.R
import com.squareup.picasso.Picasso

// Correct cards' image urls to be properly displayed
fun convertToHttps(imageUrl: String?): String? {
    return when {
        imageUrl == "null" -> null
        imageUrl?.substring(5) == "https" -> imageUrl
        else -> imageUrl?.replace("http", "https")
    }
}

// Load images from their url into the given imageView
fun loadImage(cardImage: ImageView, imageUrl: String?, size: Int) {
    Picasso.get()
        .load(convertToHttps(imageUrl))
        .resize(size, size*2)
        .centerInside()
        .placeholder(R.drawable.default_card)
        .into(cardImage)
}