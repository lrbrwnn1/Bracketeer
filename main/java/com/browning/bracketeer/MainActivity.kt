package com.browning.bracketeer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.floor

const val EXTRA_MESSAGE = "com.browning.bracketeer.MESSAGE"

class MainActivity : AppCompatActivity() {

    var id = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floorNumber.setText("" + id)

        plusButton.setOnClickListener() {
            floorNumber.setText("" + ++id)

        }
        //TODO: Add a bottom limit so user can't have negative number of floors!
        minusButton.setOnClickListener() {
            if (id > 1) {
                floorNumber.setText("" + --id)
            }
            if (id < 2) {
                Toast.makeText(applicationContext, "Building must have at least one floor! ", Toast.LENGTH_SHORT).show()
            }

        }
    }
        fun sendFloors(view: View) {
            val editText = findViewById<TextView>(R.id.floorNumber)
            val floorNumberFinal = editText.text.toString()
            val intent = Intent(this, DisplayBracketSolver::class.java).apply {
                putExtra(EXTRA_MESSAGE, floorNumberFinal)
            }
            startActivity(intent)
        }
}

