package com.kietngo.example.laws.traffic.repository.room.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kietngo.example.laws.traffic.repository.room.model.bookmark.Bookmark
import com.kietngo.example.laws.traffic.repository.room.model.bookmark.BookmarkDao
import com.kietngo.example.laws.traffic.repository.room.model.bookmarktype.BookMarkType
import com.kietngo.example.laws.traffic.repository.room.model.bookmarktype.BookmarkTypeDao
import com.kietngo.example.laws.traffic.repository.room.model.key.word.detail.KeyWordDetail
import com.kietngo.example.laws.traffic.repository.room.model.key.word.detail.KeyWordDetailDao
import com.kietngo.example.laws.traffic.repository.room.model.keyword.KeyWord
import com.kietngo.example.laws.traffic.repository.room.model.keyword.KeyWordDao
import com.kietngo.example.laws.traffic.repository.room.model.law.Law
import com.kietngo.example.laws.traffic.repository.room.model.law.LawDao
import com.kietngo.example.laws.traffic.repository.room.model.noice.board.type.NoticeBoardType
import com.kietngo.example.laws.traffic.repository.room.model.noice.board.type.NoticeBoardTypeDao
import com.kietngo.example.laws.traffic.repository.room.model.noticeboard.NoticeBoard
import com.kietngo.example.laws.traffic.repository.room.model.noticeboard.NoticeBoardDao
import com.kietngo.example.laws.traffic.repository.room.model.transport.type.TransportType
import com.kietngo.example.laws.traffic.repository.room.model.transport.type.TransportTypeDao
import com.kietngo.example.laws.traffic.repository.room.model.violation.Violation
import com.kietngo.example.laws.traffic.repository.room.model.violation.ViolationDao
import com.kietngo.example.laws.traffic.repository.room.model.violationgroup.ViolationGroup
import com.kietngo.example.laws.traffic.repository.room.model.violationgroup.ViolationGroupDao
import com.kietngo.example.laws.traffic.repository.room.model.violationrelation.ViolationRelation
import com.kietngo.example.laws.traffic.repository.room.model.violationrelation.ViolationRelationDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities =
[Bookmark::class, BookMarkType::class,KeyWord::class , KeyWordDetail::class, Law::class,
NoticeBoard::class,NoticeBoardType::class, TransportType::class, Violation::class, ViolationGroup::class,
ViolationRelation::class
]
    ,
    version = 2
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun bookMarkDao(): BookmarkDao
    abstract fun bookMarkTypeDao(): BookmarkTypeDao
    abstract fun keyWordDao(): KeyWordDao
    abstract fun keyWordDetailDao(): KeyWordDetailDao
    abstract fun lawDao(): LawDao
    abstract fun noticeBoardDao(): NoticeBoardDao
    abstract fun noticeBoardTypeDao(): NoticeBoardTypeDao
    abstract fun transportTypeDao(): TransportTypeDao
    abstract fun violationDao(): ViolationDao
    abstract fun violationGroupDao(): ViolationGroupDao
    abstract fun violationRelationDao(): ViolationRelationDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context,scope: CoroutineScope): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java, "testdatabase2"
                )
                    .createFromAsset("giao_thong.db")
                        /*neu mat data thi chinh version la dc ???? chua biet loi gi
                        * change 2 -> 3 -> 2*/
                    .fallbackToDestructiveMigration() // ???
                    .build()
                INSTANCE = instance

                instance
            }
        }

        private class AppDatabaseCallBack(
                private val scope: CoroutineScope, val context: Context
        ): RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        //ADD them laws
                    }
                }
            }
        }

    }
}