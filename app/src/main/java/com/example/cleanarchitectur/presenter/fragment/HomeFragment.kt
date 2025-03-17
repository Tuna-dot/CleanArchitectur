package com.example.cleanarchitectur.presenter.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleanarchitectur.databinding.FragmentHomeBinding
import com.example.cleanarchitectur.presenter.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val text = binding.edText.text.toString()
            val apiKey = "7d6454ad-5149-4c51-b2f9-615b091e98da:fx"
            if (text.isNotEmpty()) {
                viewModel.translateText(apiKey, text,"ru") // Запрос на перевод
            }
        }

        // Подписка на результат перевода
        viewModel.translation.observe(viewLifecycleOwner) { translation ->
            binding.text.text = translation // Отображаем перевод
        }

    }

//    @SuppressLint("SetTextI18n")
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModel.counter.observe(viewLifecycleOwner) { counter ->
//            binding.tvCounter.text = counter.count.toString()
//        }
//
//        binding.decrementButton.setOnClickListener {
//            viewModel.decrement()
//        }
//
//        binding.incrementButton.setOnClickListener {
//            viewModel.increment()
//
//        }
//    }
}