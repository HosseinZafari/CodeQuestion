package hosseinzafari.github.codequestion.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.struct.UserSignupModel
import hosseinzafari.github.codequestion.ui.data.repository.UserRepository
import hosseinzafari.github.codequestion.ui.helper.io
import hosseinzafari.github.codequestion.ui.ui.util.Resource

/*

@created in 03/08/2020 - 10:02 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class QuestionViewModel : ViewModel() {

    private val userRepository = UserRepository()

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


}