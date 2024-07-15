package com.duyvv.firstlesson.ui.bai1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.duyvv.firstlesson.base.BaseFragment
import com.duyvv.firstlesson.databinding.FragmentFirstBinding

class FirstFragment : BaseFragment<FragmentFirstBinding>() {

    private val viewModel: FirstViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFirstBinding.inflate(inflater, container, false)

    override fun init() = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
    }

    private fun setUp() {

        binding.apply {
            btnSubmit.setOnClickListener {
                onClickSubmit()
            }
        }
    }

    private fun onClickSubmit() {
        val str = binding.edtInput.text.trim().toString()
        if (str.isEmpty()) {
            return
        }
        binding.tvResult.visibility = View.VISIBLE
        var result = "Kết quả"
        viewModel.countCharacter(str).toSortedMap().forEach { (char, count) ->
            result = "$result\n'$char' : $count"
        }
        binding.tvResult.text = result
    }
}