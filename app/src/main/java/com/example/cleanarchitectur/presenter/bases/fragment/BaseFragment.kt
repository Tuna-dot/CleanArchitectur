package com.example.cleanarchitectur.presenter.bases.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.cleanarchitectur.utils.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

typealias Inflater<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val inflate: Inflater<VB>
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected inline fun <reified T : VM> vm(): Lazy<T> {
        return viewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupListeners()
        setupCollects()
        setupRequests()
    }

    protected open fun setupUI(){}

    protected open fun setupListeners(){}

    protected open fun setupCollects(){

    }

    protected open fun setupRequests(){}

    protected fun <T> StateFlow<UIState<T>>.collectState(
        state: (UIState<T>) -> Unit,
        onSuccess: (data: T) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle( state = Lifecycle.State.STARTED) {
                this@collectState.collect { uiState ->
                    state(uiState)
                    when (uiState) {
                        is UIState.Success -> onSuccess(uiState.data)
                        is UIState.Error -> showError(uiState.message)
                        else -> {}
                    }
                }
            }
        }
    }

    // Показать ошибку через Toast
    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
