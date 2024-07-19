package com.duyvv.firstlesson.ui.bai3.acitivity

import android.view.LayoutInflater
import android.view.ViewGroup
import com.duyvv.firstlesson.base.BaseFragment
import com.duyvv.firstlesson.databinding.FragmentActivityBinding


class ActivityFragment : BaseFragment<FragmentActivityBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentActivityBinding.inflate(layoutInflater, container, false)

    override fun init() {
    }
}