package com.browning.bracketeer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import android.widget.LinearLayout.LayoutParams
import kotlinx.android.synthetic.main.activity_display_bracket_solver.*
import kotlinx.android.synthetic.main.activity_display_bracket_solver.view.*
import java.io.Serializable
import java.text.DecimalFormat
import kotlin.math.ceil

class DisplayBracketSolver : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_bracket_solver)

        // Get the Intent that started this activity and extract the string
        val floorNumberFinal = intent.getStringExtra(EXTRA_MESSAGE)


        val allEDs = ArrayList<TextView>()

        for (i in 1..floorNumberFinal.toInt()){
            val edit_text: TextView = EditText(this)
            var params : LayoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT, // This will define text view width
                LayoutParams.WRAP_CONTENT // This will define text view height
            )

            edit_text.layoutParams = params
            edit_text.inputType = InputType.TYPE_CLASS_NUMBER
            edit_text.hint = "Enter height of floor " + i + " (inches)"

            edit_text.setPadding(16,16,16,16)
            edit_text.id = i
            //add the text view to the view group
            constraintLayout.addView(edit_text)
            allEDs+= edit_text
            calculateButton.setOnClickListener(){
                calculateBrackets(i, allEDs)
            }

        }
    }

    fun calculateBrackets(i: Int, allEDs: ArrayList<TextView>){
        val floorList = mutableListOf<Serializable>()
        val bracketSpan: EditText = findViewById(R.id.bracketSpan)
        var bracketCount = 0
        for (x in 0..i-1){
            floorList += allEDs.get(x).getText().toString().toInt()
        }
        for (x in floorList){
            if (bracketSpan.text.toString().toDouble() - x.toString().toDouble() >= 0){
                bracketCount += 1
            } else if (bracketSpan.text.toString().toDouble() - x.toString().toDouble() < 0){
                bracketCount += 2
            }

        }
        textView3.text = bracketCount.toString()

    }
}
