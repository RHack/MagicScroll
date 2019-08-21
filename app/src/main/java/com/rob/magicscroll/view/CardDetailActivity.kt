package com.rob.magicscroll.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rob.magicscroll.R

class CardDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail)

        val card: Bundle? = intent.extras

        // Unload card data received from the adapter
        val cardName = card?.get(CARD_NAME).toString()
        val cardManaCost = card?.get(CARD_MANA_COST).toString()
        val cardImage = card?.get(CARD_IMAGE).toString()
        val cardText = card?.get(CARD_TEXT).toString()

        val cardNameTextView = findViewById<TextView>(R.id.detail_card_name)
        val cardManaCostTextView = findViewById<TextView>(R.id.detail_card_mana_cost)
        val cardImageImageView = findViewById<ImageView>(R.id.detail_card_image)
        val cardTextTextView = findViewById<TextView>(R.id.detail_card_text)

        cardNameTextView.text = cardName
        cardManaCostTextView.text = cardManaCost
        loadImage(cardImageImageView, cardImage, DETAIL_CARD_SIZE)
        cardTextTextView.text = cardText
    }
}


