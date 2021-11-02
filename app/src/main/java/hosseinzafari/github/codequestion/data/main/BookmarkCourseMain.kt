package hosseinzafari.github.codequestion.data.main

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hosseinzafari.github.codequestion.data.Room.entity.BookmarkCourseEntity

/*

@created in 28/11/2020 - 11:35 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

interface BookmarkCourseMain  {


    suspend fun addBookmarkCourse(entity: BookmarkCourseEntity)

    suspend fun deleteBookmarkCourse(entity: BookmarkCourseEntity)

    fun getAllBookmarkedCourse(): LiveData<List<BookmarkCourseEntity>?>

    fun getBookmarkCourseById(id: Int): LiveData<BookmarkCourseEntity?>

}