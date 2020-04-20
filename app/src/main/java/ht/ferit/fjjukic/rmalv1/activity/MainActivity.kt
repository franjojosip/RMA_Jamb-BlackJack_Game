package ht.ferit.fjjukic.rmalv1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ht.ferit.fjjukic.rmalv1.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setButtonListeners()
    }

    private fun setButtonListeners(){
        val btnJamb: Button = findViewById(R.id.btnJamb)
        val btnBlackJack: Button = findViewById(R.id.btnBlackJack)

        btnJamb.setOnClickListener {
            val intent = Intent(this, JambMainActivity::class.java)
            startActivity(intent)
        }
        btnBlackJack.setOnClickListener {
            val intent = Intent(this, BlackJackMainActivity::class.java)
            startActivity(intent)
        }
    }

}
