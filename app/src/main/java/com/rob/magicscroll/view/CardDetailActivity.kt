package com.rob.magicscroll.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rob.magicscroll.R

class CardDetailActivity : AppCompatActivity() {
    private var recyclerPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail)

        val card: Bundle? = intent.extras

        val cardName = card?.get("card_name").toString()
        val cardManaCost = card?.get("card_mana_cost").toString()
        val cardImage = card?.get("card_image").toString()
        val cardText = card?.get("card_text").toString()

        val cardNameTextView = findViewById<TextView>(R.id.detail_card_name)
        val cardManaCostTextView = findViewById<TextView>(R.id.detail_card_mana_cost)
        val cardImageImageView = findViewById<ImageView>(R.id.detail_card_image)
        val cardTextTextView = findViewById<TextView>(R.id.detail_card_text)

        cardNameTextView.text = cardName
        cardManaCostTextView.text = cardManaCost
        loadImage(cardImageImageView, cardImage)
        cardTextTextView.text = cardText
    }
}


