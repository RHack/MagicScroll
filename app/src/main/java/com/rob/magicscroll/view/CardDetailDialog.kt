package com.rob.magicscroll.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.rob.magicscroll.R
import com.rob.magicscroll.model.entities.CardEntity

class CardDetailDialog(activity: Activity) : AlertDialog(activity) {
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

    val builder: AlertDialog.Builder = activity?.let {
        AlertDialog.Builder(it)
    }
//    builder.setMessage(R.string.card_dialog_dismiss)
//    var inflater: LayoutInflater = layoutInflater



}


