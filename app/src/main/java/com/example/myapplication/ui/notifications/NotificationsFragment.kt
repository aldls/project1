<<<<<<< HEAD
package com.example.myapplication.ui.notifications
=======
package com.example.myapplication.ui.test
>>>>>>> minji

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
=======
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentTestBinding
import com.example.myapplication.ui.notifications.Question


class TestFragment : Fragment() {

    private var _binding: FragmentTestBinding? = null
>>>>>>> minji

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

<<<<<<< HEAD
=======
    private lateinit var question: Question
    private val questions = listOf(
        Question("What is your favorite color?", listOf("Red", "Blue", "Green"), listOf(1, 2, 3)),
        Question("What is your favorite season?", listOf("Summer", "Winter", "Spring"), listOf(3, 1, 2)),
        Question("What is your favorite hobby?", listOf("Reading", "Sports", "Traveling"), listOf(2, 3, 1))
    )

    private var currentQuestionIndex = 0
    private val userScores = mutableListOf<Int>()

>>>>>>> minji
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
<<<<<<< HEAD
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
=======
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
>>>>>>> minji
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}