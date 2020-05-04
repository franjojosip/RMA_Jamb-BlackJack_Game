package ht.ferit.fjjukic.rmalv1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import ht.ferit.fjjukic.rmalv1.Jamb
import ht.ferit.fjjukic.rmalv1.R

class JambMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jamb_main)

        val jambGame = Jamb(6)
        setButtonListener(jambGame)
        setImageViewListener(jambGame)
        startGame(jambGame)
    }

    private fun startGame(jambGame: Jamb){
        jambGame.roll()
        setDiceImage(jambGame.getResult())
    }

    private fun setButtonListener(jambGame: Jamb){
        val btnRollADice: Button = findViewById(R.id.btnRollADice)
        val btnIsJamb: Button = findViewById(R.id.btnIsJamb)
        val btnIsPoker: Button = findViewById(R.id.btnIsPoker)
        val btnIsStraight: Button = findViewById(R.id.btnIsStraight)
        val btnBack: Button = findViewById(R.id.btnBack)

        btnRollADice.setOnClickListener {
            jambGame.roll()
            setDiceImage(jambGame.getResult())
        }
        btnIsJamb.setOnClickListener {
            Toast.makeText(this@JambMainActivity, "It's ${jambGame.checkDuplicates(5, "jamb")}", Toast.LENGTH_SHORT).show()
        }
        btnIsPoker.setOnClickListener {
            Toast.makeText(this@JambMainActivity, "It's ${jambGame.checkDuplicates(4, "poker")}", Toast.LENGTH_SHORT).show()
        }
        btnIsStraight.setOnClickListener {
            Toast.makeText(this@JambMainActivity, "It's ${jambGame.checkStraight()}", Toast.LENGTH_SHORT).show()

        }
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setImageViewListener(jambGame: Jamb){
        val imgViewDice1: ImageView = findViewById(R.id.image_dice1);
        val imgViewDice2: ImageView = findViewById(R.id.image_dice2);
        val imgViewDice3: ImageView = findViewById(R.id.image_dice3);
        val imgViewDice4: ImageView = findViewById(R.id.image_dice4);
        val imgViewDice5: ImageView = findViewById(R.id.image_dice5);
        val imgViewDice6: ImageView = findViewById(R.id.image_dice6);

        imgViewDice1.setOnClickListener {
            showMessage(jambGame.lock(0), 1)
        }
        imgViewDice2.setOnClickListener {
            showMessage(jambGame.lock(1), 2)
        }
        imgViewDice3.setOnClickListener {
            showMessage(jambGame.lock(2), 3)
        }
        imgViewDice4.setOnClickListener {
            showMessage(jambGame.lock(3), 4)
        }
        imgViewDice5.setOnClickListener {
            showMessage(jambGame.lock(4), 5)
        }
        imgViewDice6.setOnClickListener {
            showMessage(jambGame.lock(5), 6)
        }
    }

    private fun showMessage(isLocked: Boolean, diceNumber: Int){
        val lockMessage: String = when{
            isLocked -> "Dice $diceNumber locked"
            else -> "Dice $diceNumber unlocked"
        }
        Toast.makeText(this@JambMainActivity, lockMessage, Toast.LENGTH_SHORT).show()
    }

    private fun setDiceImage(result: MutableList<Int>){
        val imgViewDice1: ImageView = findViewById(R.id.image_dice1);
        val imgViewDice2: ImageView = findViewById(R.id.image_dice2);
        val imgViewDice3: ImageView = findViewById(R.id.image_dice3);
        val imgViewDice4: ImageView = findViewById(R.id.image_dice4);
        val imgViewDice5: ImageView = findViewById(R.id.image_dice5);
        val imgViewDice6: ImageView = findViewById(R.id.image_dice6);

        imgViewDice1.setImageResource(resources.getIdentifier("dice_" + result[0].toString(), "drawable", "ht.ferit.fjjukic.rmalv1"))
        imgViewDice2.setImageResource(resources.getIdentifier("dice_" + result[1].toString(), "drawable", "ht.ferit.fjjukic.rmalv1"))
        imgViewDice3.setImageResource(resources.getIdentifier("dice_" + result[2].toString(), "drawable", "ht.ferit.fjjukic.rmalv1"))
        imgViewDice4.setImageResource(resources.getIdentifier("dice_" + result[3].toString(), "drawable", "ht.ferit.fjjukic.rmalv1"))
        imgViewDice5.setImageResource(resources.getIdentifier("dice_" + result[4].toString(), "drawable", "ht.ferit.fjjukic.rmalv1"))
        imgViewDice6.setImageResource(resources.getIdentifier("dice_" + result[5].toString(), "drawable", "ht.ferit.fjjukic.rmalv1"))
    }
}
