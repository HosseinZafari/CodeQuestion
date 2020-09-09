package hosseinzafari.github.codequestion.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.gson.Gson
import hosseinzafari.github.codequestion.data.repository.SharedPrefRepository
import hosseinzafari.github.codequestion.ui.helper.ui
import hosseinzafari.github.codequestion.ui.struct.UserModel

/*

@created in 03/09/2020 - 12:04 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class ProfileViewModel : ViewModel() {

    private val sharedPrefRepository = SharedPrefRepository()


    fun getUser() = liveData {
        val userJson = ui { sharedPrefRepository.getUserJson().value }
        emit(Gson().fromJson(userJson, UserModel::class.java))
    }

}