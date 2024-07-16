package com.duyvv.firstlesson.base

import android.app.Dialog
import android.content.Context
import com.duyvv.firstlesson.R

class ProgressDialog(
    context: Context,
) : Dialog(context) {
    init {
        setContentView(R.layout.progress_dialog)
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}
