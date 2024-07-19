package com.duyvv.firstlesson.ui.bai3

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.duyvv.firstlesson.ui.bai3.acitivity.ActivityFragment
import com.duyvv.firstlesson.ui.bai3.history.HistoryFragment

class ProfileHistoryAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HistoryFragment()
            1 -> ActivityFragment()
            else -> throw IllegalStateException("Invalid position")
        }
    }
}