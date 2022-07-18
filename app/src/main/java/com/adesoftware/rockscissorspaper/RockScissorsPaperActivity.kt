package com.adesoftware.rockscissorspaper

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random
import com.adesoftware.rockscissorspaper.databinding.ActivityRockScissorsPaperBinding

class RockScissorsPaperActivity : Activity() {

    private lateinit var binding: ActivityRockScissorsPaperBinding

    private lateinit var random: Random
    private var humanScore: Int = 0
    private var computerScore: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRockScissorsPaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //assign variables
        binding.apply {
            tvScore
            tvHumanChoice
            tvComputerChoice
            ivComputerChoice
            ivHumanChoice
        }



        binding.btnRock.setOnClickListener {
            binding.ivHumanChoice.setImageResource(R.drawable.rockrot)
            val message: String = playTurn("rock")
            Toast.makeText(this, "" + message, Toast.LENGTH_LONG).show()
            binding.tvScore.text = getString(R.string.human_score) + Integer.toString(humanScore) +
                    " Computer: " + Integer.toString(computerScore)
        }

        binding.btnScissors.setOnClickListener {
            binding.ivHumanChoice.setImageResource(R.drawable.scissorsrot)
            val message: String = playTurn("scissors")
            Toast.makeText(this, "" + message, Toast.LENGTH_LONG).show()
            binding.tvScore.text = getString(R.string.human_score) + Integer.toString(humanScore) +
                    " Computer: " + Integer.toString(computerScore)
        }
        binding.btnPaper.setOnClickListener {
            binding.ivHumanChoice.setImageResource(R.drawable.paperrot)
            val message: String = playTurn("paper")
            Toast.makeText(this, "" + message, Toast.LENGTH_LONG).show()
            binding.tvScore.text = getString(R.string.human_score) + Integer.toString(humanScore) +
                    " Computer: " + Integer.toString(computerScore)
        }
    }

    private fun playTurn(playerChoice: String): String {
        var computerChoice = ""
        random = Random(10)
        //choice 1, 2 or 3
        val computerChoiceNumber = random.nextInt(3) + 1

        if (computerChoiceNumber == 1) {
            computerChoice = "rock"
        } else  if (computerChoiceNumber == 2) {
            computerChoice = "scissors"
        } else  if (computerChoiceNumber == 3) {
            computerChoice = "paper"
        }

        //Set the computer image based on computer's choice
        if (computerChoice == "rock") {
            binding.ivComputerChoice.setImageResource(R.drawable.rockrot)
        } else if (computerChoice == "scissors") {
            binding.ivComputerChoice.setImageResource(R.drawable.scissorsrot)
        } else if (computerChoice == "paper") {
            binding.ivComputerChoice.setImageResource(R.drawable.paperrot)
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