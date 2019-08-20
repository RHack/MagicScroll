package com.rob.magicscroll.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.rob.magicscroll.R
import com.rob.magicscroll.model.entities.CardEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_card.view.*

class CardListAdapter(private val context: Context, private val cards: List<CardEntity>) :
    RecyclerView.Adapter<CardListAdapter.ViewHolder>() {
    override fun getItemCount() = cards.size

    lateinit var clickListener: ClickListener


    fun setOnCardClickListener(newClickListener: ClickListener) {
        clickListener = newClickListener
    }

    private fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    interface ClickListener {
        fun onClick(pos: Int, view: View, card: CardEntity)
    }

    inner class ViewHolder(val card: ConstraintLayout) : RecyclerView.ViewHolder(card), View.OnClickListener {
        override fun onClick(v: View) {
            clickListener.onClick(card.tag as Int, v, cards[adapterPosition])
        }

        init {
            itemView.setOnClickListener(this)
        }

        fun bind() = with(card) {
            //            card.card_name.text = cards[adapterPosition].name
//            loadImage(cards[adapterPosition].imageUrl, card.card_image)
//            itemView.setOnClickListener(this)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val card = LayoutInflater.from(context).inflate(R.layout.list_item_card, parent, false)
                as ConstraintLayout

        parent.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("weee")
            builder.setView(R.layout.dialog_card)
            builder.setPositiveButton(
                "yeaah!",
                DialogInterface.OnClickListener { dialog, whichButton -> dialog.dismiss() })
            val dialog = builder.create()
            dialog.show()
        }
        return ViewHolder(card)
    }

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.card.card_name.text = cards[position].name
        loadImage(holder.card.card_image, cards[position].imageUrl)

        Log.i("${holder.card.card_name.text}: ", "${cards[position].imageUrl}")

        holder.card.tag = position
//        holder.bind()
    }


}