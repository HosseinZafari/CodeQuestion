package hosseinzafari.github.codequestion.ui.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import hosseinzafari.github.codequestion.data.repository.CourseRepository
import hosseinzafari.github.codequestion.struct.CodeModel
import hosseinzafari.github.codequestion.struct.CourseModel
import hosseinzafari.github.codequestion.ui.data.repository.CodeRepository
import hosseinzafari.github.codequestion.ui.data.repository.UserRepository
import hosseinzafari.github.codequestion.ui.helper.io
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.struct.UserModel
import hosseinzafari.github.codequestion.ui.ui.util.Resource

/*
@created in 25/07/2020 - 01:19 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class HomeViewModel : ViewModel() {

    private val repositoryUser = UserRepository()
    private val repositoryCourse = CourseRepository()
    private val repositoryCode = CodeRepository()

    fun getBestUser(): LiveData<Resource<List<UserModel>>> = liveData {
        // set loading data
        emit(Resource.loading())
        try {
            // set success data
            val users = getBestUserData()
            log("getBestUser()  Thread " + Thread.currentThread().name + users.value)
            emit(Resource.success(users.value!!))
        } catch (e: Exception) {
            // set error
            emit(Resource.error<List<UserModel>>("" + e.message))
        }
    }

    private suspend fun getBestUserData() =  io { repositoryUser.getBestUser() }

    fun getCourses(): LiveData<Resource<List<CourseModel>?>> = liveData {
        emit(Resource.loading())
        try {
            val courses = getCoursesData()
            emit(Resource.success(courses.value))
        } catch(e: Exception) {
            emit(Resource.error<List<CourseModel>>("" + e.message))
        }
    }



    private suspend fun getCoursesData() = io { repositoryCourse.getCourses() }

    fun getCodes(): LiveData<Resource<List<CodeModel>?>> = liveData {
        emit(Resource.loading())
        try {
            val codes = getCodesData()
            emit(Resource.success(codes.value))
        } catch (e: Exception) {
            emit(Resource.error<List<CodeModel>>())
        }
    }

    private suspend fun getCodesData() = io { repositoryCode.getBestCode() }
}