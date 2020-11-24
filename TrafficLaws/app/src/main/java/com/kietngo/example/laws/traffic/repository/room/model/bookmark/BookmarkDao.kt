package com.kietngo.example.laws.traffic.repository.room.model.bookmark

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.kietngo.example.laws.traffic.repository.room.model.bookmark.Bookmark

@Dao
interface BookmarkDao {
    /*get all BookMark*/
    @Query("SELECT * FROM TABLE_BOOKMARK")
    fun getAllBookMark(): List<Bookmark>

    /*get Bookmark with ID*/
    @Query("SELECT * FROM TABLE_BOOKMARK WHERE ID = :BookmarkId")
    fun getBookmarkWithId(BookmarkId : Int) : LiveData<Bookmark>
}