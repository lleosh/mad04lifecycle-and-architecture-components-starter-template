package com.example.mad03_fragments_and_navigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.mad03_fragments_and_navigation.databinding.FragmentQuizEndBinding


class QuizEndFragment : Fragment() {
    private lateinit var binding: FragmentQuizEndBinding


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_end, container, false)

        // get score from navigation arguments
        var score = arguments?.getInt("score").toString()
        var maxScore = arguments?.getInt("maxScore").toString()

        //show score
        binding.textView5.text = "$score / $maxScore"

        //button
        binding.restartBtn.setOnClickListener { view : View ->
            restartQuiz(view)
        }
        return binding.root
    }

    private fun restartQuiz(v: View) {
        v.findNavController().navigate(R.id.action_quizEndFragment_to_quizFragment)
    }
}
