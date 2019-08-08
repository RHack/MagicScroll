package com.rob.magicscroll.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rob.magicscroll.R
import com.rob.magicscroll.model.entities.CardEntity
import com.rob.magicscroll.viewModel.CardList
import io.reactivex.Observable
import kotlinx.android.synthetic.main.list_item_card.view.*

class CardListAdapter(private val cards: List<CardEntity>) :
//class CardListAdapter(private val cards: Observable<CardList>) :
    RecyclerView.Adapter<CardListAdapter.ViewHolder>() {
    private var currentList : List<CardEntity> = listOf()



    companion object {
        private const val TYPE_CHARACTER = 0
        private const val TYPE_FOOTER = 1
    }

    override fun getItemCount() = cards.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return when (viewType) {
//            TYPE_CHARACTER -> ViewHolder(parent.inflate(R.layout.list_item_card))
//            TYPE_FOOTER -> ViewHolder(parent.inflate(R.layout.list_item_loading))
//            else -> throw RuntimeException("viewType $viewType doesn't exist.")
//        }
        val inflatedView = parent.inflate(R.layout.list_item_card)
        return ViewHolder(inflatedView)
    }

    private fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == itemCount - 1) {
            holder.bind()
        } else {
            holder.bind(cards.elementAt(position))
        }
    }



    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val viewItem = view
        var cardNameTv = viewItem.card_name
        var cardImage = viewItem.card_image

        fun bind(card: CardEntity) = with(viewItem) {
            cardNameTv.text = card.name
//            cardImage.setImageSrc(card.cardImageUrl)
            setOnClickListener {
                val intent = Intent(context, CardDetailActivity::class.java)
//                intent.putExtra("Card", card)
                context.startActivity(intent)
            }
        }
        fun bind() = with(viewItem) {

        }
    }
}