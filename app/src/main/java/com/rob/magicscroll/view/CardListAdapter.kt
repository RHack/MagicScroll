package com.rob.magicscroll.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.rob.magicscroll.R
import com.rob.magicscroll.model.entities.CardEntity
import kotlinx.android.synthetic.main.list_item_card.view.*

class CardListAdapter(private val cards: List<CardEntity>) :
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
        fun onClick(pos: Int, view: View)
    }

    inner class ViewHolder(val card: ConstraintLayout) : RecyclerView.ViewHolder(card), View.OnClickListener{
        override fun onClick(v: View) {
            clickListener.onClick(adapterPosition, v)
        }
        init {
            itemView.setOnClickListener(this)
        }

        fun bind() = with(card) {
            card.card_name.text = cards[adapterPosition].name
            card.card_image.setImageResource(R.drawable.default_card)
//            itemView.setOnClickListener(this)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val card = LayoutInflater.from(context).inflate(R.layout.list_item_card, parent, false)
                as ConstraintLayout


        parent.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("wooo")
            builder.setView(R.layout.dialog_card)
            builder.setPositiveButton("yeaah!", DialogInterface.OnClickListener { dialog, whichButton -> dialog.dismiss()})
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        return ViewHolder(card)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.card.card_name.text = cards[position].name
        holder.card.card_image.setImageResource(R.drawable.default_card)

        holder.bind()
    }


}