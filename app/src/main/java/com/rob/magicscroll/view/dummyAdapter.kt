package com.rob.magicscroll.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.rob.magicscroll.R
import kotlinx.android.synthetic.main.list_item_card.view.*

class dummyAdapter(private val myDataSet: Array<String>):
        RecyclerView.Adapter<dummyAdapter.MyViewHolder>() {

    class MyViewHolder(val card: ConstraintLayout) : RecyclerView.ViewHolder(card)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dummyAdapter.MyViewHolder {
        val card = LayoutInflater.from(parent.context).inflate(R.layout.list_item_card, parent, false)
                as ConstraintLayout

        return MyViewHolder(card)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.card.card_name.text = myDataSet[position]
        holder.card.card_image.setImageResource(R.drawable.default_card)
    }

    override fun getItemCount() = myDataSet.size

}