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
        setClickOnButtons(jambGame)
        setClickOnImages(jambGame)
        startGame(jambGame)
    }

    private fun startGame(jambGame: Jamb){
        jambGame.rollDices()
        setDiceImages(jambGame.getDiceResult())
    }

    private fun setClickOnButtons(jambGame: Jamb){
        val btnRollADice: Button = findViewById(R.id.btnRollADice)
        val btnIsJamb: Button = findViewById(R.id.btnIsJamb)
        val btnIsPoker: Button = findViewById(R.id.btnIsPoker)
        val btnIsStraight: Button = findViewById(R.id.btnIsStraight)
        val btnBack: Button = findViewById(R.id.btnBack)

        btnRollADice.setOnClickListener {
            jambGame.rollDices()
            setDiceImages(jambGame.getDiceResult())
        }
        btnIsJamb.setOnClickListener {
            Toast.makeText(this@JambMainActivity, "It's ${jambGame.checkDuplicates(5)}", Toast.LENGTH_SHORT).show()
        }
        btnIsPoker.setOnClickListener {
            Toast.makeText(this@JambMainActivity, "It's ${jambGame.checkDuplicates(4)}", Toast.LENGTH_SHORT).show()
        }
        btnIsStraight.setOnClickListener {
            Toast.makeText(this@JambMainActivity, "It's ${jambGame.checkStraight()} straight", Toast.LENGTH_SHORT).show()

        }
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setClickOnImages(jambGame: Jamb){
        val imgViewDice1: ImageView = findViewById(R.id.image_dice1);
        val imgViewDice2: ImageView = findViewById(R.id.image_dice2);
        val imgViewDice3: ImageView = findViewById(R.id.image_dice3);
        val imgViewDice4: ImageView = findViewById(R.id.image_dice4);
        val imgViewDice5: ImageView = findViewById(R.id.image_dice5);
        val imgViewDice6: ImageView = findViewById(R.id.image_dice6);

        imgViewDice1.setOnClickListener {
            showLockMessage(jambGame.changeIsDiceLocked(0), 1)
        }
        imgViewDice2.setOnClickListener {
            showLockMessage(jambGame.changeIsDiceLocked(1), 2)
        }
        imgViewDice3.setOnClickListener {
            showLockMessage(jambGame.changeIsDiceLocked(2), 3)
        }
        imgViewDice4.setOnClickListener {
            showLockMessage(jambGame.changeIsDiceLocked(3), 4)
        }
        imgViewDice5.setOnClickListener {
            showLockMessage(jambGame.changeIsDiceLocked(4), 5)
        }
        imgViewDice6.setOnClickListener {
            showLockMessage(jambGame.changeIsDiceLocked(5), 6)
        }
    }

    private fun showLockMessage(isLocked: Boolean, diceNumber: Int){
        val lockMessage: String = when(isLocked){
            true -> "Dice $diceNumber locked"
            false -> "Dice $diceNumber unlocked"
        }
        Toast.makeText(this@JambMainActivity, lockMessage, Toast.LENGTH_SHORT).show()
    }

    private fun setDiceImages(result: MutableList<Int>){
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
