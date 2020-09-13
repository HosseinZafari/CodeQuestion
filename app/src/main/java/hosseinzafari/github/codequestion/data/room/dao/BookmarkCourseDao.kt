package hosseinzafari.github.codequestion.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import hosseinzafari.github.codequestion.data.room.entity.BookmarkCourseEntity

/*

@created in 13/09/2020 - 11:32 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

@Dao
interface BookmarkCourseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBookmarkCourse(entity: BookmarkCourseEntity)

    @Delete
    suspend fun deleteBookmarkCourse(entity: BookmarkCourseEntity)

    @Query("SELECT * FROM tb_bookmark_course")
    suspend fun getAllBookmarkedCourse(): LiveData<List<BookmarkCourseEntity>>

    @Query("SELECT * FROM tb_bookmark_course WHERE id IN (:id)")
    suspend fun getBookmarkCourseById(id: Int)


}