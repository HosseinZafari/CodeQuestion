package hosseinzafari.github.codequestion.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import hosseinzafari.github.codequestion.data.repository.CourseRepository
import hosseinzafari.github.codequestion.data.repository.TokenRepository
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.struct.UserSignupModel
import hosseinzafari.github.codequestion.ui.data.repository.RulesRepository
import hosseinzafari.github.codequestion.ui.data.repository.UserRepository
import hosseinzafari.github.codequestion.ui.helper.io
import hosseinzafari.github.codequestion.ui.ui.util.Resource
import kotlinx.coroutines.launch

/*

@created in 03/08/2020 - 10:02 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class QuestionViewModel : ViewModel() {

    private val userRepository    by lazy { UserRepository()  }
    private val tokenRepository  by lazy { TokenRepository() }
    private val rulesRepository   by lazy { RulesRepository() }
    private val courseRepository by lazy { CourseRepository() }

    fun signupUser(userSignupModel: UserSignupModel): LiveData<Resource<ResponseStdModel?>> = liveData {
            emit(Resource.loading())
            try {
                val response = io { userRepository.signupUser(userSignupModel) }
                emit(Resource.success(response.value))
            } catch (e: Exception) {
                emit(Resource.error())
            }
        }

    fun loginUser(email: String, password: String): LiveData<Resource<ResponseStdModel?>> = liveData {
            emit(Resource.loading())
            try {
                val response = io { userRepository.loginUser(password, email) }
                emit(Resource.success(response.value))
            } catch (e: Exception) {
                emit(Resource.error())
            }
        }

    suspend fun getToken() : LiveData<String?>   {
        return tokenRepository.getToken()
    }

    fun setToken(token: String){
        viewModelScope.launch {
            tokenRepository.setToken(token)
        }
    }

    fun getRules(): LiveData<Resource<ResponseStdModel?>> = liveData {
        emit(Resource.loading())
        try {
            val rules = io { rulesRepository.getRules() }
            emit(Resource.success(rules.value))
        } catch (e: Exception) {
            emit(Resource.error(e.message.toString()))
        }
    }

    fun getAllCourses(): LiveData<Resource<ResponseStdModel?>> = liveData {
        emit(Resource.loading())
        try {
            val courses = io { courseRepository.getAllCourses() }
            emit(Resource.success(courses.value))
        } catch(e: Exception) {
            emit(Resource.error(e.message.toString()))
        }
    }
}