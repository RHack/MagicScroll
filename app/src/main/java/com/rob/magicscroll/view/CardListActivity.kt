package com.rob.magicscroll.view


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rob.magicscroll.App
import com.rob.magicscroll.R
import com.rob.magicscroll.model.entities.CardEntity
import com.rob.magicscroll.model.entities.MockCardEntity
import com.rob.magicscroll.viewModel.CardList
import com.rob.magicscroll.viewModel.CardListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
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
    var recyclerPosition = 0

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var viewAdapter: CardListAdapter = CardListAdapter(this, emptyList())



    fun subscribe(disposable: Disposable): Disposable {
        subscriptions.add(disposable)
        return disposable
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        setContentView(R.layout.activity_card_list)



        linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.scrollToPosition(30)
//        viewAdapter = CardListAdapter(cards)
        recyclerView = findViewById<RecyclerView>(R.id.card_list_rv).apply {
            setHasFixedSize(true)

            adapter = viewAdapter
            layoutManager = linearLayoutManager
        }


    }

    override fun onStart() {
        super.onStart()



        subscribe(cardListViewModel.getCards()
            .subscribeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("Success", "${it.cards}")
                showCards(it)
            }, {
                Log.e("Error 58", it.localizedMessage)
            })
        )
    }

    fun showCards(cardList: CardList) {
        if (cardList.error == null) {
            recyclerView.adapter = CardListAdapter(this, cardList.cards)
            if(intent.extras != null) {
                recyclerPosition = intent.extras?.get("current_position").toString().toInt()

                linearLayoutManager.scrollToPosition(recyclerPosition)
            }
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