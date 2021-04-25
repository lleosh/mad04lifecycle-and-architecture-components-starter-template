package com.example.mad03_fragments_and_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mad03_fragments_and_navigation.databinding.FragmentQuizBinding
import com.example.mad03_fragments_and_navigation.models.QuestionCatalogue
//import com.example.mad03_fragments_and_navigation.models.QuizViewModel


class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    //private lateinit var viewModel: QuizViewModel
    private val questions = QuestionCatalogue().defaultQuestions    // get a list of questions for the game
    private var score = 0                                           // save the user's score
    private var index = 0                                           // index for question data to show

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)

        binding.index = index
        binding.questionsCount = questions.size
        binding.question = questions[index]

        binding.btnNext.setOnClickListener { view : View ->
            nextQuestion(view)
        }

        return binding.root
    }

    private fun nextQuestion(v: View){
        val answer = binding.answerBox.checkedRadioButtonId


        //Toast message if no answer is selected
        if(answer == -1) {
            Toast.makeText(activity, "Select an answer!",
                    Toast.LENGTH_SHORT).show()
            return
        }
        // get selected answer
        // check if is the correct answer
        // update score
        if(answer == binding.answer1.id) {
            if(questions.get(index).answers.get(0).isCorrectAnswer)
                score++
        }
        if(answer == binding.answer2.id) {
            if(questions.get(index).answers.get(1).isCorrectAnswer)
                score++
        }
        if(answer == binding.answer3.id) {
            if(questions.get(index).answers.get(2).isCorrectAnswer)
                score++
        }
        if(answer == binding.answer4.id) {
            if(questions.get(index).answers.get(3).isCorrectAnswer)
                score++
        }
        // check if any questions are left
        if(questions.size-1 > index) {
            index++
            binding.index = index
            binding.question = questions[index]
        }
        else {
            // navigate to QuizEndFragment
            val scoreBundle = bundleOf("score" to score, "maxScore" to questions.size)
            v.findNavController().navigate(R.id.action_quizFragment_to_quizEndFragment, scoreBundle)
        }

    }
}
