package com.example.cleanarchitectur.presenter.fragment

import androidx.core.view.isVisible
import com.example.cleanarchitectur.databinding.FragmentHomeBinding
import com.example.cleanarchitectur.presenter.bases.fragment.BaseFragment
import com.example.cleanarchitectur.presenter.viewmodel.HomeViewModel
import com.example.cleanarchitectur.utils.UIState

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    private val viewModel by vm<HomeViewModel>()

    override fun setupListeners() {
        binding.button.setOnClickListener {
            val text = binding.edText.text.toString()
            val apiKey = "7d6454ad-5149-4c51-b2f9-615b091e98da:fx"
            if (text.isNotEmpty()) {
                viewModel.translateText(apiKey, text, "ru")
            }
        }
    }

    override fun setupCollects() {
        viewModel.translateTextState.collectState(
            state = { state ->
                binding.progressBar.isVisible = state is UIState.Loading
            },
            onSuccess = { data ->
                binding.text.text = data
            }
        )
    }
}