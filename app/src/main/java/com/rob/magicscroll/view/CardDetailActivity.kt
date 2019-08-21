package com.rob.magicscroll.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.rob.magicscroll.R
import com.rob.magicscroll.model.entities.CardEntity

class CardDetailActivity : AppCompatActivity() {
    private var recyclerPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail)

        val card: Bundle? = intent.extras

        recyclerPosition = card?.get("current_position") as Int

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

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, CardListActivity::class.java)
        intent.putExtra("current_position", recyclerPosition)
        startActivity(intent)
        finish()
    }
//        context: Context, card: CardEntity) {
//        val cardDialog = Dialog(context)
//        cardDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        val cardView = R.layout.dialog_card
//        cardDialog.setContentView(cardView)
//        var cardNameTv = cardDialog.findViewById<TextView>(R.id.dialog_card_name)
////        var cardManaCostTv = cardDialog.findViewById<TextView>(R.id.dialog_card_mana_cost)
//        var cardImageImg = cardDialog.findViewById<ImageView>(R.id.dialog_card_image)
////        var cardDescriptionTv = cardDialog.findViewById<TextView>(R.id.dialog_card_description)
//        cardNameTv.text = card.name
////        cardManaCostTv.text = card.manaCost
////        cardDescriptionTv.text = card.text
//    }
//
//    val builder: AlertDialog.Builder = activity?.let {
//        AlertDialog.Builder(it)
//    }
//    builder.setMessage(R.string.card_dialog_dismiss)
//    var inflater: LayoutInflater = layoutInflater



}


