package com.rob.magicscroll.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rob.magicscroll.R
import com.rob.magicscroll.model.entities.CardEntity

class CardDetailActivity : AppCompatActivity() {

}

fun showCardDialog(context: Context, card: CardEntity) {
    val cardDialog = Dialog(context)
    cardDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    val cardView = R.layout.dialog_card
    cardDialog.setContentView(cardView)
    var cardNameTv = cardDialog.findViewById<TextView>(R.id.dialog_card_name)
    var cardManaCostTv = cardDialog.findViewById<TextView>(R.id.dialog_card_mana_cost)
    var cardImageImg = cardDialog.findViewById<ImageView>(R.id.dialog_card_image)
    var cardDescriptionTv = cardDialog.findViewById<TextView>(R.id.dialog_card_description)
    cardNameTv.text = card.name
    cardManaCostTv.text = card.manaCost
    cardDescriptionTv.text = card.text
}