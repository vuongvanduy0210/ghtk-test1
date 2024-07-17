package com.duyvv.firstlesson.ui.bai1

import com.duyvv.firstlesson.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor() : BaseViewModel() {

    fun countCharacter(str: String): HashMap<Char, Int> {
        val hashMap = HashMap<Char, Int>()
        for (char in str) {
            val count = hashMap[char]
            if (count == null || count <= 0) {
                hashMap[char] = 1
            } else {
                hashMap[char] = count + 1
            }
        }
        return hashMap
    }
}
