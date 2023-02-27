package com.example.worldle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*
import com.example.worldle.FourLetterWordList as FourLetterWordList1

class MainActivity : AppCompatActivity() {
    val wordToGuess = com.example.worldle.FourLetterWordList().getRandomFourLetterWord()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guess1 = findViewById<TextView>(R.id.guess1Answer)
        val guess1Checker = findViewById<TextView>(R.id.guess1CheckerAnswer)
        val guess2 = findViewById<TextView>(R.id.guess2Answer)
        val guess2Checker = findViewById<TextView>(R.id.guess2CheckAnswer)
        val guess3 = findViewById<TextView>(R.id.guess3Answer)
        val guess3Checker = findViewById<TextView>(R.id.guess3CheckAnswer)


        val displayWord = findViewById<TextView>(R.id.wordleWord)
        displayWord.text = wordToGuess

        val enteredGuess = findViewById<EditText>(R.id.enterGuess)
        var counter = 0

        findViewById<Button>(R.id.btnguess).setOnClickListener {

            if (enteredGuess.text.isNullOrEmpty())
                Toast.makeText(this,
                    "Please Guess a 4 Letter Word",
                    Toast.LENGTH_SHORT
                ).show()
            else
            {
                counter++

                if (counter == 1)
                {
                    guess1.text = enteredGuess.text.toString()
                    val checker = checkGuess(enteredGuess.text.toString().uppercase())
                    guess1Checker.text = checker

                    if (checker == "OOOO")
                    {
                        Toast.makeText(this,"Congratulations, You WON!", Toast.LENGTH_LONG).show()
                        enteredGuess.visibility = View.INVISIBLE
                        findViewById<Button>(R.id.btnguess).visibility = View.INVISIBLE
                        findViewById<Button>(R.id.btnRestart).visibility = View.VISIBLE
                    }
                }

                else if (counter == 2)
                {
                    guess2.text = enteredGuess.text.toString()
                    val checker = checkGuess(enteredGuess.text.toString().uppercase())
                    guess2Checker.text = checker

                    if (checker == "OOOO")
                    {
                        Toast.makeText(this,"Congratulations, You WON!", Toast.LENGTH_LONG).show()
                        enteredGuess.visibility = View.INVISIBLE
                        findViewById<Button>(R.id.btnguess).visibility = View.INVISIBLE
                        findViewById<Button>(R.id.btnRestart).visibility = View.VISIBLE
                    }
                }

                else
                {
                    guess3.text = enteredGuess.text.toString()
                    val checker = checkGuess(enteredGuess.text.toString().uppercase())
                    guess3Checker.text = checker
                    displayWord.visibility = View.VISIBLE

                    if (checker == "OOOO")
                        Toast.makeText(this,"Congratulations, You WON!", Toast.LENGTH_SHORT).show()

                    else
                        Toast.makeText(this,"Sorry, You Lost!", Toast.LENGTH_SHORT).show()

                    enteredGuess.visibility = View.INVISIBLE
                    findViewById<Button>(R.id.btnguess).visibility = View.INVISIBLE
                    findViewById<Button>(R.id.btnRestart).visibility = View.VISIBLE
                }
            }
        }

        findViewById<Button>(R.id.btnRestart).setOnClickListener {
            this.recreate()
            enteredGuess.text.clear()
        }


    }

    private fun checkGuess(guess: String,) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
                Log.i("Debug", "Found Exact")
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
                Log.i("Debug", "Found similar")
            }
            else {
                result += "X"
                Log.i("Debug", "Not Found")
            }
        }
        return result
    }
}