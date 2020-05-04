package ht.ferit.fjjukic.rmalv1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.*
import ht.ferit.fjjukic.rmalv1.BlackJack
import ht.ferit.fjjukic.rmalv1.Card
import ht.ferit.fjjukic.rmalv1.R
import java.util.*

class BlackJackMainActivity : AppCompatActivity() {
    private lateinit var blackJack: BlackJack
    private lateinit var resultPc: TextView
    private lateinit var resultUser: TextView
    private lateinit var handler: Handler
    private lateinit var btnHit: Button
    private lateinit var btnStand: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blackjack_main)

        this.blackJack = BlackJack()
        this.handler = Handler()

        setTextViews()
        setButtonListener()
        startGame()
    }

    private fun setButtonListener() {
        this.btnHit = findViewById(R.id.btnHit)
        this.btnStand = findViewById(R.id.btnStand)
        val btnBack: Button = findViewById(R.id.btnBack)

        this.btnHit.setOnClickListener {
            pullCard()
        }
        this.btnStand.setOnClickListener {
            this.btnHit.isEnabled = false
            this.btnStand.isEnabled = false
            val userResult: Int = this.blackJack.getResult(true)
            val pcResult: Int = this.blackJack.getResult(false)
            checkResult(pcResult, userResult)
        }
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setTextViews() {
        resultPc = findViewById(R.id.tvComputer)
        resultUser = findViewById(R.id.tvUser)
    }

    private fun pullCard(){
        this.blackJack.pullCard(true)
        val userResult: Int = this.blackJack.getResult(true)
        val pcResult: Int = this.blackJack.getResult(false)
        resultUser.text = "Your Result: $userResult"
        resultPc.text = "Computer Result: $pcResult"
        setCardImage(true)
        if(userResult >= 21){
            this.btnHit.isEnabled = false
            this.btnStand.isEnabled = false
            checkResult(pcResult, userResult)
        }
    }

    private fun startGame() {
        this.blackJack.pullCard(false)
        this.blackJack.pullCard(true)
        this.blackJack.pullCard(true)
        setCardImage(true)
        setCardImage(false)

        val userResult: Int = this.blackJack.getResult(true)
        val pcResult: Int = this.blackJack.getResult(false)
        resultUser.text = "Your Result: $userResult"
        resultPc.text = "Computer Result: $pcResult"

        if (userResult == 21) {
            checkResult(pcResult, userResult)
        }
    }

    private fun checkResult(pcResult: Int, userResult: Int) {
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
            this.blackJack.pullCard(false)
            val pcResult2: Int = this.blackJack.getResult(false)
            resultPc.text = "Computer Result: $pcResult2"
            setCardImage(false)
            handler.postDelayed({
                checkResult(pcResult2, userResult)
            }, 1000)
        }
    }

    private fun setCardImage(isUser: Boolean) {
        val cards: MutableList<Card> = this.blackJack.getCards(isUser)
        val cardLayout: LinearLayout = if(isUser){
            findViewById(R.id.userCards)
        } else findViewById(R.id.computerCards)

        cardLayout.removeAllViews()

        if(cards.count() > 1){
            for(card: Card in cards) {
                val imageView = ImageView(this)
                val density: Float = resources.displayMetrics.density
                imageView.layoutParams = LinearLayout.LayoutParams((60 * density).toInt(), (120 * density).toInt())
                val imageId: Int = resources.getIdentifier(card.getName().toLowerCase(Locale.getDefault()),"drawable", packageName)
                imageView.setImageResource(imageId)
                cardLayout.addView(imageView)
            }
        }
        else{
            val imageView1 = ImageView(this)
            val imageView2 = ImageView(this)
            val density: Float = resources.displayMetrics.density
            imageView1.layoutParams = LinearLayout.LayoutParams((60 * density).toInt(), (120 * density).toInt())
            imageView2.layoutParams = LinearLayout.LayoutParams((60 * density).toInt(), (120 * density).toInt())
            val firstImageId: Int = resources.getIdentifier(cards.first().getName().toLowerCase(Locale.getDefault()),"drawable", packageName)
            val secondImageId: Int = resources.getIdentifier("back","drawable", packageName)
            imageView1.setImageResource(firstImageId)
            imageView2.setImageResource(secondImageId)
            cardLayout.addView(imageView1)
            cardLayout.addView(imageView2)
        }
    }

    private fun resetGame() {
        handler.postDelayed({
            val cardLayoutUser: LinearLayout = findViewById(R.id.userCards)
            val cardLayoutPC: LinearLayout = findViewById(R.id.computerCards)
            cardLayoutUser.removeAllViews()
            cardLayoutPC.removeAllViews()
            this.btnHit.isEnabled = true
            this.btnStand.isEnabled = true
            this.blackJack.reset()
            startGame()
        }, 3000)
    }
}
