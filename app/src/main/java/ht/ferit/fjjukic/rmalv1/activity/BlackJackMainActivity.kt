package ht.ferit.fjjukic.rmalv1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import ht.ferit.fjjukic.rmalv1.BlackJack
import ht.ferit.fjjukic.rmalv1.Card
import ht.ferit.fjjukic.rmalv1.Deck
import ht.ferit.fjjukic.rmalv1.R
import java.util.*
import kotlin.concurrent.schedule

class BlackJackMainActivity : AppCompatActivity() {
    private lateinit var blackJack: BlackJack
    private lateinit var deck: Deck
    private lateinit var pcImgView1: ImageView
    private lateinit var pcImgView2: ImageView
    private lateinit var pcImgView3: ImageView
    private lateinit var pcImgView4: ImageView
    private lateinit var pcImgView5: ImageView
    private lateinit var userImgView1: ImageView
    private lateinit var userImgView2: ImageView
    private lateinit var userImgView3: ImageView
    private lateinit var userImgView4: ImageView
    private lateinit var userImgView5: ImageView
    private lateinit var resultPc: TextView
    private lateinit var resultUser: TextView
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blackjack_main)

        this.blackJack = BlackJack()
        this.deck = createDeck()
        this.handler = Handler()
        setViews()
        setButtonListener()
        startGame()
    }

    private fun createDeck(): Deck {
        var cards: MutableList<Card> = mutableListOf()
        val clubs: List<String> = listOf("c", "d", "h", "s")
        for(i: Int in 0..3){
            for(j: Int in 1..13){
                when(j){
                    1 -> cards.add(
                        Card(
                            1,
                            "a${clubs[i]}"
                        )
                    )
                    11 -> cards.add(
                        Card(
                            10,
                            "j${clubs[i]}"
                        )
                    )
                    12 -> cards.add(
                        Card(
                            10,
                            "q${clubs[i]}"
                        )
                    )
                    13 -> cards.add(
                        Card(
                            10,
                            "k${clubs[i]}"
                        )
                    )
                    else -> cards.add(
                        Card(
                            j,
                            "${clubs[i]}$j"
                        )
                    )
                }
            }
        }
        return Deck(cards)
    }

    private fun setButtonListener(){
        val btnHit: Button = findViewById(R.id.btnHit)
        val btnBack: Button = findViewById(R.id.btnBack)
        val btnStand: Button = findViewById(R.id.btnStand)

        btnHit.setOnClickListener {
            var card: Card = this.deck.getRandomCard()
            blackJack.addCard(true, card)
            val userResult: Int = this.blackJack.getCardResult(true)
            var pcResult: Int = this.blackJack.getCardResult(false)
            resultPc.text = "Your Result: $userResult"
            setCardImage(card, true)
            checkUserResult(userResult, pcResult)
        }
        btnStand.setOnClickListener {
            btnHit.isClickable = false
            btnStand.isClickable = false
            val userResult: Int = this.blackJack.getCardResult(true)
            var pcResult: Int = this.blackJack.getCardResult(false)
            checkResultStand(pcResult, userResult)
        }
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setViews(){
        pcImgView1 = findViewById(R.id.pcImgView1)
        pcImgView2 = findViewById(R.id.pcImgView2)
        pcImgView3 = findViewById(R.id.pcImgView3)
        pcImgView4 = findViewById(R.id.pcImgView4)
        pcImgView5 = findViewById(R.id.pcImgView5)
        userImgView1 = findViewById(R.id.userImgView1)
        userImgView2 = findViewById(R.id.userImgView2)
        userImgView3 = findViewById(R.id.userImgView3)
        userImgView4 = findViewById(R.id.userImgView4)
        userImgView5 = findViewById(R.id.userImgView5)
        resultPc = findViewById(R.id.tvComputer)
        resultUser = findViewById(R.id.tvUser)
    }

    private fun startGame(){
        val pcCard1: Card = this.deck.getRandomCard()
        val userCard1: Card = this.deck.getRandomCard()
        val userCard2: Card = this.deck.getRandomCard()

        this.blackJack.addCard(false, pcCard1)
        this.blackJack.addCard(true, userCard1)
        this.blackJack.addCard(true, userCard2)
        pcImgView1.setImageResource(resources.getIdentifier(pcCard1.getName(), "drawable", "ht.ferit.fjjukic.rmalv1"))
        pcImgView2.setImageResource(resources.getIdentifier("back", "drawable", "ht.ferit.fjjukic.rmalv1"))
        userImgView1.setImageResource(resources.getIdentifier(userCard1.getName(), "drawable", "ht.ferit.fjjukic.rmalv1"))
        userImgView2.setImageResource(resources.getIdentifier(userCard2.getName(), "drawable", "ht.ferit.fjjukic.rmalv1"))

        val userResult: Int = this.blackJack.getCardResult(true)
        val pcResult: Int = this.blackJack.getCardResult(false)
        resultUser.text = "Your Result: $userResult"
        resultPc.text = "Computer Result: $pcResult"

        if(userResult == 21){
            checkResultStand(pcResult, userResult)
        }
    }

    private fun checkResultStand(pcResult: Int, userResult: Int) {
        if (userResult > 21 || (pcResult in 16..20 && pcResult > userResult)) {
            Toast.makeText(this@BlackJackMainActivity, "USER LOSE", Toast.LENGTH_SHORT).show()
            resetGame()
        } else if (pcResult > 21 || (pcResult in 16..20 && pcResult < userResult)) {
            Toast.makeText(this@BlackJackMainActivity, "USER WIN", Toast.LENGTH_SHORT).show()
            resetGame()
        } else if (pcResult >= 16 && pcResult == userResult) {
            Toast.makeText(this@BlackJackMainActivity, "TIE", Toast.LENGTH_SHORT).show()
            resetGame()
        } else {
            var card: Card = this.deck.getRandomCard()
            blackJack.addCard(false, card)
            var pcResult2 = this.blackJack.getCardResult(false)
            resultPc.text = "Computer Result: $pcResult2"
            setCardImage(card, false)
            handler.postDelayed({
                checkResultStand(pcResult2, userResult)
            }, 3000)
        }
    }

    private fun setCardImage(card: Card, isUser: Boolean){
        var cardCount: Int = when (isUser){
            true -> blackJack.countUserCards()
            false -> blackJack.countComputerCards()
        }
        when (cardCount) {
            2 -> {
                when (isUser) {
                    true -> userImgView2.setImageResource(
                        resources.getIdentifier(
                            card.getName(),
                            "drawable",
                            "ht.ferit.fjjukic.rmalv1"
                        )
                    )
                    false -> pcImgView2.setImageResource(
                        resources.getIdentifier(
                            card.getName(),
                            "drawable",
                            "ht.ferit.fjjukic.rmalv1"
                        )
                    )
                }
            }
            3 -> {
                when (isUser) {
                    true -> userImgView3.setImageResource(
                        resources.getIdentifier(
                            card.getName(),
                            "drawable",
                            "ht.ferit.fjjukic.rmalv1"
                        )
                    )
                    false -> pcImgView3.setImageResource(
                        resources.getIdentifier(
                            card.getName(),
                            "drawable",
                            "ht.ferit.fjjukic.rmalv1"
                        )
                    )
                }
            }
            4 -> {
                when (isUser) {
                    true -> userImgView4.setImageResource(
                        resources.getIdentifier(
                            card.getName(),
                            "drawable",
                            "ht.ferit.fjjukic.rmalv1"
                        )
                    )
                    false -> pcImgView4.setImageResource(
                        resources.getIdentifier(
                            card.getName(),
                            "drawable",
                            "ht.ferit.fjjukic.rmalv1"
                        )
                    )
                }
            }
            else -> {
            when (isUser) {
                true -> userImgView5.setImageResource(
                    resources.getIdentifier(
                        card.getName(),
                        "drawable",
                        "ht.ferit.fjjukic.rmalv1"
                    )
                )
                false -> pcImgView5.setImageResource(
                    resources.getIdentifier(
                        card.getName(),
                        "drawable",
                        "ht.ferit.fjjukic.rmalv1"
                    )
                )
            }
        }
        }
    }

    private fun checkUserResult(userResult: Int, pcResult: Int){
        if (userResult > 21) {
            Toast.makeText(this@BlackJackMainActivity, "USER LOSE", Toast.LENGTH_SHORT).show()
            resetGame()
        }
    }

    private fun resetGame(){
        val intent = Intent(this, BlackJackMainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
