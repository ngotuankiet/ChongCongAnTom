package com.kietngo.example.laws.traffic.repository.room.model.noice.board.type

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.kietngo.example.laws.traffic.repository.room.model.noice.board.type.NoticeBoardType

@Dao
interface NoticeBoardTypeDao {
    /*get all Notice board type*/
    @Query("SELECT * FROM TABLE_NOTICE_BOARD_TYPE")
    fun  getALlNoticeBoardType(): List<NoticeBoardType>

    /*get notice broad with id*/
//    @Query("SELECT * FROM TABLE_NOTICE_BOARD_TYPE WHERE Type_ID = :typeId")
//    fun getNoticeBoardTypeWithId(typeId: Int): LiveData<NoticeBoardType>
}