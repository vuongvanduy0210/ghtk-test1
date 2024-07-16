package com.duyvv.firstlesson.ui.bai1

import com.duyvv.firstlesson.base.BaseViewModel

class FirstViewModel : BaseViewModel() {
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
