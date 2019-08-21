package com.rob.magicscroll.view

import android.os.Bundle
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
    private lateinit var cardListRecyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val cardListViewModel = App.injectCardListViewModel()
    private val cardSubscriptions = CompositeDisposable()
    private var recyclerPosition = 0
    private var viewAdapter: CardListAdapter = CardListAdapter(this, emptyList())

    val cards: List<CardEntity> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)
        linearLayoutManager = LinearLayoutManager(this)

        // Initialize recyclerView
        cardListRecyclerView = findViewById<RecyclerView>(R.id.card_list_rv).apply {
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = linearLayoutManager
        }

        // Get the list of cards and display them if successful
        subscribeCards(
            cardListViewModel.getCards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    showCards(it)
                }, {
                    Log.e("Error 58", it.localizedMessage)
                })
        )
    }

    // Combine card disposables into a composite disposable
    private fun subscribeCards(cardDisposable: Disposable): Disposable {
        cardSubscriptions.add(cardDisposable)
        return cardDisposable
    }

    // Display the list of cards on the recyclerView
    private fun showCards(cardList: CardList) {
        if (cardList.error == null) {
            cardListRecyclerView.adapter = CardListAdapter(this, cardList.cards)
            linearLayoutManager.scrollToPosition(recyclerPosition)
        } else if (cardList.error is ConnectException || cardList.error is UnknownHostException) {
            Log.e("Network Error", "Connection Lost", cardList.error)
        } else {
            Log.e("Error", "Something went wrong", cardList.error)
        }
    }

    // Save the current position of the recyclerView
    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        recyclerPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()
        state.putInt(RECYCLER_POSITION, recyclerPosition)
    }

    // Load the saved position of the recyclerView
    override fun onRestoreInstanceState(state: Bundle) {
        super.onRestoreInstanceState(state)
        if (state != NULL_STATE) {
            recyclerPosition = state.getInt(RECYCLER_POSITION)
        }
    }

    override fun onStop() {
        super.onStop()
        cardSubscriptions.clear()
    }
}