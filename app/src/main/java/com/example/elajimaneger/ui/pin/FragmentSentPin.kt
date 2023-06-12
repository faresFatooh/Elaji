package com.example.elajimaneger.ui.pin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.elajimaneger.databinding.FragmentSentPinBinding

class FragmentSentPin : Fragment() {
    private var binding: FragmentSentPinBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSentPinBinding.inflate(layoutInflater, container, false)
        binding!!.buSend.setOnClickListener { _: View? -> }
        return binding!!.root
    }

}