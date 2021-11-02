package hosseinzafari.github.codequestion.data.Room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import hosseinzafari.github.codequestion.data.Room.entity.BookmarkCourseEntity


/*
    @created in 13/09/2020 - 11:32 AM
    @project Code Question
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

@Dao
interface BookmarkCourseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBookmarkCourse(entity: BookmarkCourseEntity)

    @Delete
    fun deleteBookmarkCourse(entity: BookmarkCourseEntity)

    @Query("SELECT * FROM tb_bookmark_course")
    fun getAllBookmarkedCourse(): LiveData<List<BookmarkCourseEntity>?>

    @Query("SELECT * FROM tb_bookmark_course WHERE id IN (:id)")
    fun getBookmarkCourseById(id: Int): LiveData<BookmarkCourseEntity?>

}