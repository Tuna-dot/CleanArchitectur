package com.example.cleanarchitectur.presenter.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitectur.databinding.FragmentHomeBinding
import com.example.cleanarchitectur.presenter.bases.fragment.BaseFragment
import com.example.cleanarchitectur.presenter.viewmodel.HomeViewModel
import com.example.cleanarchitectur.presenter.viewmodel.HomeViewModel.UIState
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
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
}