package com.duyvv.firstlesson.ui

import com.duyvv.firstlesson.base.BaseActivity
import com.duyvv.firstlesson.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun createBinding() = ActivityMainBinding.inflate(layoutInflater)

    override val context = this
}
