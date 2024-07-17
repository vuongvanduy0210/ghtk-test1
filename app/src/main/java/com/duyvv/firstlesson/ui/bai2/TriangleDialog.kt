package com.duyvv.firstlesson.ui.bai2

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.duyvv.firstlesson.R
import com.duyvv.firstlesson.databinding.DialogTriangleBinding

class TriangleDialog(
    context: Context,
    private val isInside: Boolean,
) : Dialog(context) {
    private var binding: DialogTriangleBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogTriangleBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        binding?.tvTitle?.apply {
            text =
                if (isInside) {
                    setTextColor(ResourcesCompat.getColor(resources, R.color.green, null))
                    "Point is inside the triangle"
                } else {
                    setTextColor(Color.RED)
                    "Point is outside the triangle"
                }
        }
        binding?.btnCancel?.setOnClickListener {
            dismiss()
        }
    }
}
