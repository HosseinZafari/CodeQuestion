package hosseinzafari.github.codequestion.ui.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hosseinzafari.github.codequestion.struct.CourseModel
import hosseinzafari.github.codequestion.ui.data.main.CourseMain
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.network.api

/*
@created in 25/07/2020 - 12:13 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class CourseRemoteDataSource : CourseMain {

    private var _courseRemoteDataSource = MutableLiveData<List<CourseModel>>()

     override suspend fun getCourses(): LiveData<List<CourseModel>>  {
         log("getCourses remoteDataSource thread : " + Thread.currentThread().name )
        _courseRemoteDataSource.postValue(api.getCourses())
        return _courseRemoteDataSource
    }

}