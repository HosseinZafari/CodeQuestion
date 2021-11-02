package hosseinzafari.github.codequestion.data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import hosseinzafari.github.codequestion.data.Room.dao.BookmarkCourseDao
import hosseinzafari.github.codequestion.data.Room.entity.BookmarkCourseEntity


/*
    @created in 13/09/2020 - 09:30 AM
    @project Code Question
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

@Database(entities = arrayOf(BookmarkCourseEntity::class) , version = 1 , exportSchema = false)
abstract class AppDb : RoomDatabase(){
    abstract fun getBookmarkCourseDao(): BookmarkCourseDao

}
