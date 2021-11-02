package hosseinzafari.github.codequestion.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import hosseinzafari.github.codequestion.data.Room.entity.BookmarkCourseEntity
import hosseinzafari.github.codequestion.data.repository.CourseRepository
import hosseinzafari.github.codequestion.struct.CourseModel
import hosseinzafari.github.codequestion.ui.helper.io
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.ui.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
    @created in 28/11/2020 - 10:06 AM
    @project Code Question
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

class CourseViewModel : ViewModel() {
    private val courseRepository: CourseRepository by lazy { CourseRepository() }

    fun getAllCourse(): LiveData<Resource<List<CourseModel>>> = liveData {
        emit(Resource.loading<List<CourseModel>>())
        try {
            val data = io {
                Log.i("SOGHRA" , "get All Course in io ${Thread.currentThread().name}")
                courseRepository.getAllCourses()
            }
            Log.i("SOGHRA" ,"get All Course out of io ${Thread.currentThread().name}")
            emit(Resource.success<List<CourseModel>>(data.value?.courses!!))
        } catch (e: Exception) {
            emit(Resource.error<List<CourseModel>>())
        }
    }

    fun addBookmarkCourse(entity: BookmarkCourseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            log("This Thread is " + Thread.currentThread())
            courseRepository.addBookmarkCourse(entity)
        }
    }

    fun deleteBookmarkCourse(entity: BookmarkCourseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            log("This Thread is " + Thread.currentThread())
            courseRepository.deleteBookmarkCourse(entity)
        }
    }


    fun getBookmarkCourseById(id: Int) = courseRepository.getBookmarkCourseById(id)


    fun getAllBookmarkCourse() = courseRepository.getAllBookmarkedCourse()
}