package hosseinzafari.github.codequestion.ui.data.repository

import androidx.lifecycle.LiveData
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.struct.UserSignupModel
import hosseinzafari.github.codequestion.data.datasource.remote.UserRemoteDataSource
import hosseinzafari.github.codequestion.ui.data.main.UserMain
import hosseinzafari.github.codequestion.ui.struct.UserModel

/*
    @created in 25/07/2020 - 12:23 PM
    @project Code Question
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

class UserRepository : UserMain{
    private val userRemote = UserRemoteDataSource()

    override suspend fun getBestUser(): LiveData<List<UserModel>> {
        return userRemote.getBestUser()
    }

    override suspend fun signupUser(userSignupModel: UserSignupModel): LiveData<ResponseStdModel> {
        return userRemote.signupUser(userSignupModel)
    }

    override suspend fun loginUser(password: String, email: String): LiveData<ResponseStdModel> {
        return userRemote.loginUser(password , email)
    }

}