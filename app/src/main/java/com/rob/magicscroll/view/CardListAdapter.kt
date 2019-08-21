package com.rob.magicscroll.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.rob.magicscroll.R
import com.rob.magicscroll.model.entities.CardEntity
import kotlinx.android.synthetic.main.list_item_card.view.*

class CardListAdapter(private val context: Context, private val cards: List<CardEntity>) :
    RecyclerView.Adapter<CardListAdapter.ViewHolder>() {
    lateinit var clickListener: ClickListener

    override fun getItemCount() = cards.size

    interface ClickListener {
        fun onClick(pos: Int, view: View, card: CardEntity)
    }

    inner class ViewHolder(val card: ConstraintLayout) : RecyclerView.ViewHolder(card), View.OnClickListener {
        override fun onClick(v: View) {
            clickListener.onClick(adapterPosition, v, cards[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val card = LayoutInflater
            .from(context)
            .inflate(R.layout.list_item_card, parent, false)
                as ConstraintLayout

        return ViewHolder(card)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get card data from the list of CardEntities
        val cardName = cards[position].name
        val cardImage = cards[position].imageUrl
        val cardManaCost = cards[position].manaCost
        val cardText = cards[position].text

        // Set the card name and image on the viewHolder
        holder.card.card_name.text = cardName
        loadImage(holder.card.card_image, cardImage, 100)

        // When clicked, open CardDetailActivity with card data given
        holder.card.setOnClickListener {
            val intent = Intent(context, CardDetailActivity::class.java)
            intent.putExtra("card_name", cardName)
            intent.putExtra("card_image", cardImage)
            intent.putExtra("card_mana_cost", cardManaCost)
            intent.putExtra("card_text", cardText)
            context.startActivity(intent)
        }
    }


}