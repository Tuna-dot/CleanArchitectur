package com.example.cleanarchitectur.presenter.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchitectur.databinding.FragmentHomeBinding
import com.example.cleanarchitectur.presenter.adapters.CharacterAdapter
import com.example.cleanarchitectur.presenter.bases.fragment.BaseFragment
import com.example.cleanarchitectur.presenter.viewmodel.HomeViewModel
import com.example.cleanarchitectur.utils.UIState
import kotlinx.coroutines.flow.collectLatest

@Suppress("DEPRECATION")
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    private val viewModel by vm<HomeViewModel>()
    private val characterAdapter by lazy { CharacterAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launchWhenStarted {
            viewModel.characterState.collectLatest { state ->
                when (state) {
                    is UIState.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }
                    is UIState.PagingSuccess -> {
                        characterAdapter.submitData(state.pagingData)
                    }
                    else -> Unit
                }
            }
        }

        viewModel.getCharacters(page = 1)
    }
//    override fun setupListeners() {
//        binding.button.setOnClickListener {
//            val text = binding.edText.text.toString()
//            val apiKey = "7d6454ad-5149-4c51-b2f9-615b091e98da:fx"
//            if (text.isNotEmpty()) {
//                viewModel.translateText(apiKey, text, "ru")
//            }
//        }
//    }
//
//    override fun setupCollects() {
//        viewModel.translateTextState.collectState(
//            state = { state ->
//                binding.progressBar.isVisible = state is UIState.Loading
//            },
//            onSuccess = { data ->
//                binding.text.text = data
//            }
//        )
//    }
}