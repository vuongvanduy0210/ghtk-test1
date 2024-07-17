package com.duyvv.firstlesson.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.duyvv.firstlesson.R

class ProgressDialog(
    context: Context,
) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_progress)
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}
