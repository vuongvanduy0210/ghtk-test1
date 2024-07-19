package com.duyvv.firstlesson.ui.bai3.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.duyvv.firstlesson.base.BaseFragment
import com.duyvv.firstlesson.databinding.FragmentHistoryBinding
import com.duyvv.firstlesson.ui.bai3.ProfileViewModel
import kotlinx.coroutines.launch


class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHistoryBinding.inflate(inflater, container, false)

    private val historyAdapter: HistoryAdapter by lazy { HistoryAdapter() }

    private lateinit var viewModel: ProfileViewModel

    override fun init() {
        viewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()
    }

    private fun setup() {
        binding.rcvHistory.apply {
            adapter = historyAdapter
        }

        lifecycleScope.launch {
            viewModel.profile.collect {
                historyAdapter.setItems(it.histories ?: emptyList())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        historyAdapter.setItems(viewModel.profile.value.histories ?: emptyList())
    }
}