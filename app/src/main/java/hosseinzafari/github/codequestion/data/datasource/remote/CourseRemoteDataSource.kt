package hosseinzafari.github.codequestion.data.datasource.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.ui.data.main.CourseMain
import hosseinzafari.github.codequestion.ui.network.api

/*
@created in 25/07/2020 - 12:13 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class CourseRemoteDataSource : CourseMain {

    private var _courseRemoteDataSource     = MutableLiveData<ResponseStdModel>()
    private var _allCourseRemoteDataSource  = MutableLiveData<ResponseStdModel>()

     override suspend fun getBestCourses(): LiveData<ResponseStdModel>  {
        _courseRemoteDataSource.postValue(api.getBestCourses())
        return _courseRemoteDataSource
    }

    override suspend fun getAllCourses(): LiveData<ResponseStdModel> {
        _allCourseRemoteDataSource.postValue(api.getAllCourses())
        return _allCourseRemoteDataSource
    }

}