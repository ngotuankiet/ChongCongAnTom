package com.kietngo.example.laws.traffic.repository.repository

import androidx.lifecycle.LiveData
import com.kietngo.example.laws.traffic.repository.room.model.keyword.KeyWord
import com.kietngo.example.laws.traffic.repository.room.model.keyword.KeyWordDao

class KeyWordRepository(private val keyWordDao: KeyWordDao) {
    fun getAllKeyWord(): LiveData<List<KeyWord>> = keyWordDao.getAllKeyWord()
}