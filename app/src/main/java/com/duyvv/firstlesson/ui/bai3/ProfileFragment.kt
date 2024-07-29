package com.duyvv.firstlesson.ui.bai3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.duyvv.firstlesson.base.BaseFragment
import com.duyvv.firstlesson.databinding.FragmentProfileBinding
import com.duyvv.firstlesson.ui.MainActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    private var activity: MainActivity? = null

    private val profileHistoryAdapter: ProfileHistoryAdapter by lazy {
        ProfileHistoryAdapter(this)
    }

    private lateinit var viewModel: ProfileViewModel

    override fun init() {
        activity = requireActivity() as MainActivity
        viewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
    }

    override fun onResume() {
        super.onResume()

        // fetch data from json file in raw
        viewModel.getProfile()
    }

    private fun setUp() {
        // show loading icon and handle error
        setUpLoading()

        // setup tablayout
        setupTabLayout()

        collectLifecycleFlow(viewModel.profile) {
            binding.tvName.text = it.fullName
            binding.tvPosition.text = it.position
        }
    }

    private fun setupTabLayout() {
        binding.viewPager.adapter = profileHistoryAdapter
        binding.tabLayout.apply {
            background = null
        }
        binding.viewPager.apply {
            isUserInputEnabled = false
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Hành trình"
                1 -> "Hoạt động"
                else -> ""
            }
        }.attach()
    }

    private fun setUpLoading() {
        collectLifecycleFlow(viewModel.isLoading) {
            activity?.showLoading(isShow = it)
        }
        collectLifecycleFlow(viewModel.responseMessage) {
            activity?.showMessage(it.message, it.bgType)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModelStore.clear()
    }
}
