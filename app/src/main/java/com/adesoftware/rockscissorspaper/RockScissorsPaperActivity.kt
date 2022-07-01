package com.adesoftware.rockscissorspaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class RockScissorsPaperActivity : AppCompatActivity() {

    lateinit var bRock: Button
    lateinit var bScissors: Button
    lateinit var bPaper: Button
    lateinit var tvScore: TextView
    lateinit var tvHumanChoice: TextView
    lateinit var tvComputerChoice: TextView
    lateinit var ivComputerChoice: ImageView
    lateinit var ivHumanChoice: ImageView
    lateinit var random: Random
    private var humanScore: Int = 0
    private var computerScore: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rock_scissors_paper)

        //assign variables
        bRock = findViewById(R.id.btn_rock)
        bScissors = findViewById(R.id.btn_scissors)
        bPaper = findViewById(R.id.btn_paper)
        tvScore = findViewById(R.id.tv_score)
        tvHumanChoice = findViewById(R.id.tv_humanChoice)
        tvComputerChoice = findViewById(R.id.tv_computerChoice)
        ivComputerChoice = findViewById(R.id.iv_computerChoice)
        ivHumanChoice = findViewById(R.id.iv_humanChoice)

        bRock.setOnClickListener {
            ivHumanChoice.setImageResource(R.drawable.rockrot)
            val message: String = playTurn("rock")
            Toast.makeText(this, "" + message, Toast.LENGTH_LONG).show()
            tvScore.text = getString(R.string.human_score) + Integer.toString(humanScore) +
                    " Computer: " + Integer.toString(computerScore)
        }
        bScissors.setOnClickListener {
            ivHumanChoice.setImageResource(R.drawable.scissorsrot)
            val message: String = playTurn("scissors")
            Toast.makeText(this, "" + message, Toast.LENGTH_LONG).show()
            tvScore.text = getString(R.string.human_score) + Integer.toString(humanScore) +
                    " Computer: " + Integer.toString(computerScore)
        }
        bPaper.setOnClickListener {
            ivHumanChoice.setImageResource(R.drawable.paperrot)
            val message: String = playTurn("paper")
            Toast.makeText(this, "" + message, Toast.LENGTH_LONG).show()
            tvScore.text = getString(R.string.human_score) + Integer.toString(humanScore) +
                    " Computer: " + Integer.toString(computerScore)
        }
    }

    private fun playTurn(playerChoice: String): String {
        var computerChoice = ""
        random = Random(10)
        //choice 1, 2 or 3
        var computerChoiceNumber = random.nextInt(3) + 1

        if (computerChoiceNumber == 1) {
            computerChoice = "rock"
        } else  if (computerChoiceNumber == 2) {
            computerChoice = "scissors"
        } else  if (computerChoiceNumber == 3) {
            computerChoice = "paper"
        }

        //Set the computer image based on computer's choice
        if (computerChoice == "rock") {
            ivComputerChoice.setImageResource(R.drawable.rockrot)
        } else if (computerChoice == "scissors") {
            ivComputerChoice.setImageResource(R.drawable.scissorsrot)
        } else if (computerChoice == "paper") {
            ivComputerChoice.setImageResource(R.drawable.paperrot)
        }

        //Human and computer choice compare to determine who won
        if (computerChoice == playerChoice) {
            return "Draw. Nobody won"
        } else if (computerChoice == "scissors" && playerChoice == "rock") {
            humanScore++
            return "Rock crushes the scissors. You win!"
        } else if (computerChoice == "paper" && playerChoice == "rock") {
            computerScore++
            return "Paper covers rock. Computer wins!"
        } else if (computerChoice == "rock" && playerChoice == "scissors") {
            computerScore++
            return "Rock crushes the scissors. Computer win!"
        } else if (computerChoice == "paper" && playerChoice == "scissors") {
            humanScore++
            return "Scissors cut paper. You win!"
        } else if (computerChoice == "rock" && playerChoice == "paper") {
            humanScore++
            return "Paper covers rock. You wins!"
        } else if (computerChoice == "scissors" && playerChoice == "paper") {
            computerScore++
            return "Scissors cut paper. Computer wins!"
        } else return "not sure"
    }
}