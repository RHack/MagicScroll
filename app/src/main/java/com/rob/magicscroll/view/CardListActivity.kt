package com.rob.magicscroll.view


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rob.magicscroll.App
import com.rob.magicscroll.R
import com.rob.magicscroll.model.entities.CardEntity
import com.rob.magicscroll.model.entities.MockCardEntity
import com.rob.magicscroll.viewModel.CardList
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_card.*
import kotlinx.android.synthetic.main.dialog_card.view.*
import kotlinx.android.synthetic.main.list_item_card.view.*
import java.net.ConnectException
import java.net.UnknownHostException

class CardListActivity : AppCompatActivity() {
    val subscriptions = CompositeDisposable()
    val cardListViewModel = App.injectCardListViewModel()
    val cards : List<CardEntity> = emptyList()

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewAdapter: CardListAdapter


    fun subscribe(disposable: Disposable): Disposable {
        subscriptions.add(disposable)
        return disposable
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        setContentView(R.layout.activity_card_list)
        subscribe(cardListViewModel.getCards().subscribeOn(Schedulers.io())
            .subscribe({
                showCards(it)
            }, {
                Log.e("Error","Something went wrong")
            }
            ))
        linearLayoutManager = LinearLayoutManager(this)
//        viewAdapter = CardListAdapter(cards)
        viewAdapter.setOnCardClickListener(object: CardListAdapter.ClickListener {
            override fun onClick(pos: Int, view: View, card: CardEntity) {
                val builder = AlertDialog.Builder(context)
                val inflater = layoutInflater
                val dialogLayout = inflater.inflate(R.layout.dialog_card, null)
                dialogLayout.dialog_card_name.text = card.name
                builder.setTitle("")
                builder.setView(dialogLayout)
                builder.setPositiveButton("yeaah!", DialogInterface.OnClickListener { dialog, whichButton -> dialog.dismiss()})
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        })

        recyclerView = findViewById<RecyclerView>(R.id.card_list_rv).apply {
            setHasFixedSize(true)

            adapter = viewAdapter
            layoutManager = linearLayoutManager
        }
    }

    fun showCards(cardList: CardList) {
        if (cardList.error == null) {
            viewAdapter = CardListAdapter(cardList.cards)
        } else if (cardList.error is ConnectException || cardList.error is UnknownHostException) {
            Log.e("Network Error", "Connection Lost", cardList.error)
        } else {
            Log.e("Error","Something went wrong", cardList.error)
        }
    }

    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }

}