package com.rob.magicscroll.view

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rob.magicscroll.App
import com.rob.magicscroll.R
import com.rob.magicscroll.model.entities.CardEntity
import com.rob.magicscroll.viewModel.CardList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.net.ConnectException
import java.net.UnknownHostException

class CardListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val subscriptions = CompositeDisposable()
    private val cardListViewModel = App.injectCardListViewModel()
    private var recyclerPosition = 0
    private var viewAdapter: CardListAdapter = CardListAdapter(this, emptyList())

    val cards : List<CardEntity> = emptyList()


    private fun subscribe(disposable: Disposable): Disposable {
        subscriptions.add(disposable)
        return disposable
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.card_list_rv).apply {
            setHasFixedSize(true)

            adapter = viewAdapter
            layoutManager = linearLayoutManager
        }

        subscribe(cardListViewModel.getCards()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showCards(it)
            }, {
                Log.e("Error 58", it.localizedMessage)
            })
        )
    }

    override fun onRestoreInstanceState(state: Bundle) {
        super.onRestoreInstanceState(state)

        if(state != null) {
            recyclerPosition = state.getInt("recycler_position")
        }
    }

    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)

        recyclerPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()
        state.putInt("recycler_position", recyclerPosition)
    }

    private fun showCards(cardList: CardList) {
        if (cardList.error == null) {
            recyclerView.adapter = CardListAdapter(this, cardList.cards)
            linearLayoutManager.scrollToPosition(recyclerPosition)
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