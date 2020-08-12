package hosseinzafari.github.codequestion.data.repository

import androidx.lifecycle.LiveData
import hosseinzafari.github.codequestion.data.datasource.remote.CourseRemoteDataSource
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.ui.data.main.CourseMain

/*

@created in 28/07/2020 - 04:20 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class CourseRepository : CourseMain{
    private val courseRemoteDataSource = CourseRemoteDataSource()

    override suspend fun getBestCourses(): LiveData<ResponseStdModel> {
        return courseRemoteDataSource.getBestCourses()
    }

    override suspend fun getAllCourses(): LiveData<ResponseStdModel> {
        return courseRemoteDataSource.getAllCourses()
    }
}