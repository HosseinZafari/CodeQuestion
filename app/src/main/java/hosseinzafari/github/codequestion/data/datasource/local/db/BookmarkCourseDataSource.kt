package hosseinzafari.github.codequestion.data.datasource.local.db

import androidx.lifecycle.LiveData
import hosseinzafari.github.codequestion.data.Room.entity.BookmarkCourseEntity
import hosseinzafari.github.codequestion.data.main.BookmarkCourseMain
import hosseinzafari.github.framework.core.app.G

/*
    @created in 28/11/2020 - 10:27 AM
    @project Code Question
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

class BookmarkCourseDataSource : BookmarkCourseMain {
    private val bookmarkCourseDao by lazy {  G.database.getBookmarkCourseDao()  }


    override suspend fun addBookmarkCourse(entity: BookmarkCourseEntity) = bookmarkCourseDao.addBookmarkCourse(entity)

    override suspend fun deleteBookmarkCourse(entity: BookmarkCourseEntity) = bookmarkCourseDao.deleteBookmarkCourse(entity)

    override fun getAllBookmarkedCourse(): LiveData<List<BookmarkCourseEntity>?> = bookmarkCourseDao.getAllBookmarkedCourse()

    override fun getBookmarkCourseById(id: Int): LiveData<BookmarkCourseEntity?> = bookmarkCourseDao.getBookmarkCourseById(id)

}