package com.example.cleanarchitectur.presenter.bases.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitectur.databinding.FragmentHomeBinding
import com.example.cleanarchitectur.presenter.viewmodel.HomeViewModel
import com.example.cleanarchitectur.presenter.viewmodel.HomeViewModel.UIState
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : FragmentHomeBinding, VM : HomeViewModel> : Fragment() {

    // ViewBinding
    protected lateinit var binding: VB
    protected open val viewModel: HomeViewModel by viewModel()

    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateBinding(inflater, container)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    // Наблюдение за состоянием ViewModel
    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.translateTextState.collect { state ->
                when (state) {
                    is UIState.Loading -> showLoading()
                    is UIState.Success -> showContent(state.data)
                    is UIState.Error -> showError(state.message)
                    else -> {}
                }
            }
        }
    }

    // Показать прогресс-бар
    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    // Показать содержимое
    private fun showContent(data: String) {
        binding.progressBar.visibility = View.GONE
        binding.text.text = data
    }

    // Показать ошибку через Toast
    private fun showError(message: String) {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        Log.e("ololo", "showError: $message ", )
    }
}
