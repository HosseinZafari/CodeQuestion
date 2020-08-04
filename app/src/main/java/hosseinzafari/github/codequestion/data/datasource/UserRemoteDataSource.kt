package hosseinzafari.github.codequestion.ui.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.struct.UserSignupModel
import hosseinzafari.github.codequestion.ui.data.main.UserMain
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.network.api
import hosseinzafari.github.codequestion.ui.struct.UserModel

/*

@created in 25/07/2020 - 12:13 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class UserRemoteDataSource : UserMain {

    private var _bestUserLiveData = MutableLiveData<List<UserModel>>()
    private var _signupLiveData = MutableLiveData<ResponseStdModel>()
    private var _loginLiveData = MutableLiveData<ResponseStdModel>()

     override suspend fun getBestUser(): LiveData<List<UserModel>>  {
         log("userRemoteDataSource getBestUser thread : " + Thread.currentThread().name )
        _bestUserLiveData.postValue(api.getBestUser())
        return _bestUserLiveData
    }

    override suspend fun signupUser(userSignupModel: UserSignupModel): LiveData<ResponseStdModel> {
        log("userRemoteDataSource signup thread : " + Thread.currentThread().name )
        _signupLiveData.postValue(api.signupUser(userSignupModel))
        return _signupLiveData
    }

    override suspend fun loginUser(password: String, email: String): LiveData<ResponseStdModel> {
        _loginLiveData.postValue(api.loginUser(email , password))
        return _loginLiveData
    }

}