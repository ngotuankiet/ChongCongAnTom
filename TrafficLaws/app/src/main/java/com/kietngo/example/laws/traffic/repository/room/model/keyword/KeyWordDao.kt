package com.kietngo.example.laws.traffic.repository.room.model.keyword

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface KeyWordDao {

    @Query("SELECT * FROM TABLE_KEYWORD")
    fun getAllKeyWord(): LiveData<List<KeyWord>>
}