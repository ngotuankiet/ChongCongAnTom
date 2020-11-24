package com.kietngo.example.laws.traffic.repository.room.model.key.word.detail

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.kietngo.example.laws.traffic.repository.room.model.key.word.detail.KeyWordDetail

@Dao
interface KeyWordDetailDao {
    /*get all key word detail*/
    @Query("SELECT * FROM TABLE_KEYWORD_DETAIL")
    fun getALlKeyWordDetail(): List<KeyWordDetail>

    /*get key work with id */
//    @Query("SELECT * FROM TABLE_KEYWORD_DETAIL WHERE ID = :keyWordId")
//    fun  getKeyWordDetailWithID(keyWordId: Int) : LiveData<KeyWordDetail>

}