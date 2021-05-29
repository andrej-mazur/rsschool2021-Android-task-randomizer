package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {

    private var secondFragmentStarter: SecondFragmentStarter? = null

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minEditText: EditText? = null
    private var maxEditText: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SecondFragmentStarter) {
            secondFragmentStarter = context
        } else {
            throw RuntimeException("$context must implement SecondFragmentStarter")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minEditText = view.findViewById(R.id.min_value)
        maxEditText = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {
            val minStringValue = minEditText?.text.toString()
            val maxStringValue = maxEditText?.text.toString()

            if (minStringValue.isBlank()) {
                Toast.makeText(this.context, "Min value must be specified", Toast.LENGTH_SHORT).show()
                minEditText?.requestFocus()
                return@setOnClickListener
            }

            if (maxStringValue.isBlank()) {
                Toast.makeText(this.context, "Max value must be specified", Toast.LENGTH_SHORT).show()
                maxEditText?.requestFocus()
                return@setOnClickListener
            }

            val min = minStringValue.toIntOrNull() ?: -1
            val max = maxStringValue.toIntOrNull() ?: -1

            if (min < 0 || min > Int.MAX_VALUE) {
                Toast.makeText(this.context, "Min value must be between ${1} and ${Int.MAX_VALUE}", Toast.LENGTH_SHORT).show()
                minEditText?.requestFocus()
                return@setOnClickListener
            }

            if (max < 0 || max > Int.MAX_VALUE) {
                Toast.makeText(this.context, "Max value must be between ${1} and ${Int.MAX_VALUE}", Toast.LENGTH_SHORT).show()
                maxEditText?.requestFocus()
                return@setOnClickListener
            }

            if (min > max) {
                Toast.makeText(this.context, "Min value must not exceed the max value", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            secondFragmentStarter?.startSecondFragment(min, max)
        }
    }

    /**
     * androidx-core-ktx
     * requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {  }
     */

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val args = Bundle().apply {
                putInt(PREVIOUS_RESULT_KEY, previousResult)
            }
            val fragment = FirstFragment()
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}