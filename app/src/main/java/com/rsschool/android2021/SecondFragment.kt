package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    private var firstFragmentStarter: FirstFragmentStarter? = null
    private var backButton: Button? = null
    private var resultTextView: TextView? = null
    private var generatedResult: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FirstFragmentStarter) {
            firstFragmentStarter = context
        } else {
            throw RuntimeException("$context must implement FirstFragmentStarter")
        }

        val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                firstFragmentStarter?.startFirstFragment(generatedResult)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultTextView = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        generatedResult = generate(min, max)

        resultTextView?.text = generatedResult.toString()

        backButton?.setOnClickListener {
            firstFragmentStarter?.startFirstFragment(generatedResult)
        }
    }

    private fun generate(min: Int, max: Int): Int {
        return IntRange(min, max).random()
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val args = Bundle().apply {
                putInt(MIN_VALUE_KEY, min)
                putInt(MAX_VALUE_KEY, max)
            }
            val fragment = SecondFragment()
            fragment.arguments = args
            return fragment
        }

        const val MIN_VALUE_KEY = "MIN_VALUE"
        const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}