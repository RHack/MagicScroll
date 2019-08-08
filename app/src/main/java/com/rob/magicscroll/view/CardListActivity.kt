package com.rob.magicscroll.view


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rob.magicscroll.App
import com.rob.magicscroll.R
import com.rob.magicscroll.model.entities.CardEntity
import com.rob.magicscroll.repository.CardRepository
import com.rob.magicscroll.viewModel.CardList
import com.rob.magicscroll.viewModel.CardListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_card_list.*
import java.net.ConnectException
import java.net.UnknownHostException

class CardListActivity : AppCompatActivity() {
    val subscriptions = CompositeDisposable()
    val cardListViewModel = App.injectCardListViewModel()
    val cards : List<CardEntity> = emptyList()
    val cardNames = arrayOf("a", "b", "c", "d", "e", "1", "2","3", "4", "5", "6", "7", "8", "9", "10")
    private lateinit var recyclerView: RecyclerView
    private lateinit var  linearLayoutManager: LinearLayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>


    fun subscribe(disposable: Disposable): Disposable {
        subscriptions.add(disposable)
        return disposable
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)

        linearLayoutManager = LinearLayoutManager(this)
        viewAdapter = CardListAdapter(cards)
        subscribe(cardListViewModel.getCards().subscribeOn(Schedulers.io())
            .subscribe({
                showCards(it)
            }, {
                Log.e("Error","Something went wrong")
            }
            ))

        recyclerView = findViewById<RecyclerView>(R.id.card_list_rv).apply {
            setHasFixedSize(true)

            layoutManager = linearLayoutManager
            adapter = viewAdapter
        }


//        card_list_rv.layoutManager = linearLayoutManager

//        adapter = CardListAdapter(cardListViewModel
//        card_list_rv.adapter = CardListAdapter(cardListViewModel.getCards())
//        card_list_rv.adapter = CardListAdapter(cardListViewModel.getCards()) {
//            Toast.makeText(this, "${it.cardName} Clicked", Toast.LENGTH_LONG).show()
//        }
//        subscribe(cardListViewModel.getCards().subscribeOn(Schedulers.io())
//            .subscribe({
//                showCards(it)
//            }, {
//                Log.e("Error","Something went wrong")
//            }
//            ))
    }

    override fun onStart() {
        super.onStart()
//        subscribe(cardListViewModel.getCards().subscribeOn(Schedulers.io())
//            .subscribe({
//                showCards(it)
//            }, {
//                Log.e("Error","Something went wrong")
//            }
//            ))
    }

    fun showCards(cardList: CardList) {
        if (cardList.error == null) {
            viewAdapter = CardListAdapter(cardList.cards)
//            viewAdapter = carCardListAdapter(cards)
//            {

//                Toast.makeText(this, "${it.cardName} Clicked", Toast.LENGTH_LONG).show()
//            }
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