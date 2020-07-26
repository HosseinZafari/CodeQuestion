package hosseinzafari.github.codequestion.ui.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import hosseinzafari.github.codequestion.ui.data.repository.UserRepository
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.struct.UserModel
import hosseinzafari.github.codequestion.ui.ui.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*
@created in 25/07/2020 - 01:19 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class HomeViewModel : ViewModel() {

    private val repositoryUser = UserRepository()

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

    private suspend fun getBestUserData(): LiveData<List<UserModel>> {
        return withContext(Dispatchers.IO) {
            repositoryUser.getBestUser()
        }
    }
}