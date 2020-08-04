package hosseinzafari.github.codequestion.ui.data.main

import androidx.lifecycle.LiveData
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.struct.UserSignupModel
import hosseinzafari.github.codequestion.ui.struct.UserModel

/*
@created in 25/07/2020 - 12:11 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

interface UserMain {
    
    suspend fun getBestUser() : LiveData<List<UserModel>>

    suspend fun signupUser(userSignupModel: UserSignupModel): LiveData<ResponseStdModel>

    suspend fun loginUser(password: String , email: String): LiveData<ResponseStdModel>
}