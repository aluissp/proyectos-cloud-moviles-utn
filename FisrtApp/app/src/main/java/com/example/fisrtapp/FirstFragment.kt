package com.example.fisrtapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.fisrtapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.toast_button).setOnClickListener {
            val myToast = Toast.makeText(context, "Hello toast!", Toast.LENGTH_LONG)
            myToast.show()
        }
//        binding.toastButton.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

        binding.countButton.setOnClickListener { countMe(view) }

//        view.findViewById<Button>(R.id.random_button).setOnClickListener {
//            val showCountTextView = view.findViewById<TextView>(R.id.textview_first)
//            val currentCount = showCountTextView.text.toString().toInt()
//            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount)
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    private fun countMe(view: View) {
        val textLabel = view.findViewById<TextView>(R.id.textview_first)
        val countString = textLabel.text.toString()
        var count = countString.toInt()
        count++
        textLabel.text = count.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}