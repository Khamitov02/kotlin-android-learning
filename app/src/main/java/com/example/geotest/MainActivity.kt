package com.example.geotest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.geotest.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var trueBution: Button
    private lateinit var audioManager: Audi
    private lateinit var binding: ActivityMainBinding
    private val questionBank = listOf(
        Question(R.string.question_england, true),
        Question(R.string.question_russia, false),
        Question(R.string.question_china, true)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.questionTextView.setOnClickListener{
            Snackbar.make(it, R.string.next_button, Snackbar.LENGTH_SHORT).show()
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        binding.trueButton.setOnClickListener {
            checkAnswer(true)

        }
        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }
        binding.nextButton.setOnClickListener {
            Snackbar.make(it, R.string.next_toast, Snackbar.LENGTH_SHORT).show()
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        binding.previousButton.setOnClickListener {
            Snackbar.make(it, R.string.previous_toast, Snackbar.LENGTH_SHORT).show()
            currentIndex = if (currentIndex>0)(currentIndex -1) % questionBank.size else currentIndex
            updateQuestion()
        }
        updateQuestion()
    }

    private fun checkAnswer(userAns: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (correctAnswer == userAns) R.string.correct_toast
                                                    else R.string.incorrect_toast
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show()

    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }
}