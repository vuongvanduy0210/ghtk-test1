package com.duyvv.firstlesson.ui.bai3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.duyvv.firstlesson.base.BaseFragment
import com.duyvv.firstlesson.databinding.FragmentProfileBinding
import com.duyvv.firstlesson.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    private var activity: MainActivity? = null

    private lateinit var historyAdapter: HistoryAdapter

    private val viewModel: ProfileViewModel by viewModels()

    override fun init() {
        activity = requireActivity() as MainActivity
        historyAdapter = HistoryAdapter()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
        // fetch data from json file in raw
        viewModel.getProfile()
    }

    private fun setUp() {
        binding.rcvHistory.apply {
            adapter = historyAdapter
        }

        // show loading icon and handle error
        setUpLoading()

        lifecycleScope.launch {
            viewModel.profile.collect {
                binding.tvName.text = it.fullName
                binding.tvPosition.text = it.position
                historyAdapter.setItems(it.histories ?: emptyList())
            }
        }
    }

    private fun setUpLoading() {
        lifecycleScope.launch {
            viewModel.isLoading.collect {
                activity?.showLoading(isShow = it)
            }
        }

        lifecycleScope.launch {
            viewModel.responseMessage.collect {
                activity?.showMessage(it.message, it.bgType)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModelStore.clear()
    }
}
