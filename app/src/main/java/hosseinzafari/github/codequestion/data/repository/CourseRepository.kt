package hosseinzafari.github.codequestion.data.repository

import androidx.lifecycle.LiveData
import hosseinzafari.github.codequestion.data.Room.entity.BookmarkCourseEntity
import hosseinzafari.github.codequestion.data.datasource.local.db.BookmarkCourseDataSource
import hosseinzafari.github.codequestion.data.datasource.remote.CourseRemoteDataSource
import hosseinzafari.github.codequestion.data.main.BookmarkCourseMain
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.ui.data.main.CourseMain

/*

@created in 28/07/2020 - 04:20 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class CourseRepository : CourseMain , BookmarkCourseMain{
    private val courseRemoteDataSource by lazy { CourseRemoteDataSource() }
    private val bookmarkCourseDataSource by lazy { BookmarkCourseDataSource() }
    
    override suspend fun getBestCourses(): LiveData<ResponseStdModel> {
        return courseRemoteDataSource.getBestCourses()
    }

    override suspend fun getAllCourses(): LiveData<ResponseStdModel> {
        return courseRemoteDataSource.getAllCourses()
    }

    override suspend fun addBookmarkCourse(entity: BookmarkCourseEntity) = bookmarkCourseDataSource.addBookmarkCourse(entity)

    override suspend fun deleteBookmarkCourse(entity: BookmarkCourseEntity) = bookmarkCourseDataSource.deleteBookmarkCourse(entity)

    override fun getAllBookmarkedCourse(): LiveData<List<BookmarkCourseEntity>?> = bookmarkCourseDataSource.getAllBookmarkedCourse()

    override fun getBookmarkCourseById(id: Int): LiveData<BookmarkCourseEntity?> = bookmarkCourseDataSource.getBookmarkCourseById(id)
}