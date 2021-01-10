package com.kietngo.example.laws.traffic.repository.repository

import androidx.lifecycle.LiveData
import com.kietngo.example.laws.traffic.repository.room.model.key.word.detail.KeyWordDetail
import com.kietngo.example.laws.traffic.repository.room.model.key.word.detail.KeyWordDetailDao

class KeyWordDetailRepository(private val keyWordDetailDao : KeyWordDetailDao) {
    fun getAllKeyWordDetail(): LiveData<List<KeyWordDetail>> = keyWordDetailDao.getALlKeyWordDetail()
}