package com.duyvv.firstlesson.ui.bai2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duyvv.firstlesson.base.BaseFragment
import com.duyvv.firstlesson.databinding.FragmentSecondBinding
import com.duyvv.firstlesson.domain.Point


class SecondFragment : BaseFragment<FragmentSecondBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSecondBinding.inflate(inflater, container, false)

    override fun init() = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDraw.setOnClickListener {
            binding.triangleView.setPoints(
                Point(100.0f, 200.0f),
                Point(300.0f, 400.0f),
                Point(200.0f, 500.0f)
            )
        }
    }
}