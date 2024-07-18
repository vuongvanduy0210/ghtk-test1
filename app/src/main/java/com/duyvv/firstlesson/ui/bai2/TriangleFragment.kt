package com.duyvv.firstlesson.ui.bai2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duyvv.firstlesson.base.BaseFragment
import com.duyvv.firstlesson.databinding.FragmentTriangleBinding
import com.duyvv.firstlesson.domain.Point
import com.duyvv.firstlesson.ui.MainActivity
import com.duyvv.firstlesson.ui.common.BGType


class TriangleFragment : BaseFragment<FragmentTriangleBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentTriangleBinding.inflate(inflater, container, false)

    private var activity: MainActivity? = null

    override fun init() {
        activity = requireActivity() as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
    }

    private fun setUp() {
        binding.btnSubmit.setOnClickListener {
            drawTriangle()
        }
    }

    private fun drawTriangle() {
        activity?.hideKeyboard()
        getPointFromInput()?.let {
            binding.triangleView.setPoints(it[0], it[1], it[2])
        }
    }

    private fun getPointFromInput(): List<Point>? =
        try {
            val p1x = binding.edtP1X.text.trim().toString().toFloat()
            val p1y = binding.edtP1Y.text.trim().toString().toFloat()
            val p2x = binding.edtP2X.text.trim().toString().toFloat()
            val p2y = binding.edtP2Y.text.trim().toString().toFloat()
            val p3x = binding.edtP3X.text.trim().toString().toFloat()
            val p3y = binding.edtP3Y.text.trim().toString().toFloat()
            listOf(
                Point(p1x, p1y),
                Point(p2x, p2y),
                Point(p3x, p3y),
            )
        } catch (e: IllegalArgumentException) {
            activity?.showMessage(
                "Wrong input!",
                BGType.BG_TYPE_ERROR,
            )
            null
        }
}