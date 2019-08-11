package com.rob.magicscroll.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.rob.magicscroll.R
import com.rob.magicscroll.model.entities.MockCardEntity
import kotlinx.android.synthetic.main.list_item_card.view.*

class DummyAdapter(private val myDataSet: List<MockCardEntity>):
        RecyclerView.Adapter<DummyAdapter.MyViewHolder>() {

    lateinit var clickListener: ClickListener

    fun setOnCardClickListener(newClickListener: ClickListener) {
        clickListener = newClickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, view: View)
    }

    inner class ViewHolder(cardView: View) : RecyclerView.ViewHolder(cardView), View.OnClickListener {
        override fun onClick(v: View) {
            clickListener.onClick(adapterPosition, v)
        }
        val cardName = itemView.card_name
        init {
            itemView.setOnClickListener(this)
        }
    }
    class MyViewHolder(val card: ConstraintLayout) : RecyclerView.ViewHolder(card)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyAdapter.MyViewHolder {
        val context = parent.context
        val card = LayoutInflater.from(context).inflate(R.layout.list_item_card, parent, false)
                as ConstraintLayout


        parent.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("wooo")
            builder.setView(R.layout.dialog_card)
            builder.setPositiveButton("yeaah!", DialogInterface.OnClickListener {dialog, whichButton -> dialog.dismiss()})
            val dialog: AlertDialog = builder.create()
            dialog.show()
//            val intent = Intent(context, CardDetailDialog::class.java)
////                intent.putExtra("Card", card)
//            context.startActivity(intent)
        }
        return MyViewHolder(card)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.card.card_name.text = myDataSet[position].name
        holder.card.card_image.setImageResource(R.drawable.default_card)
    }

    override fun getItemCount() = myDataSet.size


}