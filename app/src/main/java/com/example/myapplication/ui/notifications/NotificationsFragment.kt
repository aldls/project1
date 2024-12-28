package com.example.myapplication.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentTestBinding
import com.example.myapplication.ui.notifications.Question


class TestFragment : Fragment() {

    private var _binding: FragmentTestBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var question: Question
    private val questions = listOf(
        Question("What is your favorite color?", listOf("Red", "Blue", "Green"), listOf(1, 2, 3)),
        Question("What is your favorite season?", listOf("Summer", "Winter", "Spring"), listOf(3, 1, 2)),
        Question("What is your favorite hobby?", listOf("Reading", "Sports", "Traveling"), listOf(2, 3, 1))
    )

    private var currentQuestionIndex = 0
    private val userScores = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayQuestion()

        binding.nextButton.setOnClickListener{
            val selectedId = binding.answerGroup.checkedRadioButtonId

            if(selectedId != -1){
                val selectedAnserIndex = binding.answerGroup.indexOfChild(view.findViewById(selectedId))
                userScores.add(questions[currentQuestionIndex].scores[selectedAnserIndex])

                if(currentQuestionIndex < questions.size - 1){
                    currentQuestionIndex++
                    displayQuestion()
                }
                else{
                    showResult()
                }
                }
            else{
                Toast.makeText(requireContext(), "Please select an answer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayQuestion(){
        val currentQuestion = questions[currentQuestionIndex]
        binding.questionText.text = currentQuestion.question
        currentQuestion.answers.forEachIndexed { index, answer ->
            (binding.answerGroup.getChildAt(index) as RadioButton).text = answer
        }
        binding.answerGroup.clearCheck()

//        마지막 질문에서 button 바꾸기
        if(currentQuestionIndex == questions.size - 1){
            binding.nextButton.text = "Submit"
        }
        else{
            binding.nextButton.text = "Next"
        }
    }

    private fun showResult(){
        val totalScore = userScores.sum()
        val result = when{
            totalScore <= 5 -> "You are a beginner"
            totalScore <= 8 -> "You are an intermediate"

            else -> "You are an expert"
        }

        Toast.makeText(requireContext(), result, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}