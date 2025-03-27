package com.example.cleanarchitectur.presenter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitectur.databinding.FragmentHomeBinding
import com.example.cleanarchitectur.presenter.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
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
        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        binding.button.setOnClickListener {
            val text = binding.edText.text.toString()
            val apiKey = "7d6454ad-5149-4c51-b2f9-615b091e98da:fx"
            if (text.isNotEmpty()) {
                viewModel.translateText(apiKey, text, "ru") // Запрос на перевод
            }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                if (state is HomeViewModel.UIState.Success) {
                    showTranslation(state.data)
                }
            }
        }
    }

    private fun showTranslation(text: String) {
        // Показываем переведенный текст и скрываем ошибку
        binding.text.visibility = View.VISIBLE
        binding.text.text = text
     }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

