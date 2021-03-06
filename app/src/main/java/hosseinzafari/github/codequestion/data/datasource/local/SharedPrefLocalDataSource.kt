package hosseinzafari.github.codequestion.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hosseinzafari.github.codequestion.data.SharedPref
import hosseinzafari.github.codequestion.data.main.SharedPrefMain

/*

@created in 07/08/2020 - 10:52 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class SharedPrefLocalDataSource : SharedPrefMain {
    private val _tokenMutableLiveData = MutableLiveData<String?>()
    private val _userInfoMutableLiveData = MutableLiveData<String?>()

    override suspend fun setToken(token: String) = SharedPref.setToken(token)

    override fun getToken(): LiveData<String?> {
        _tokenMutableLiveData.postValue(SharedPref.getToken())
        return _tokenMutableLiveData
    }

    override suspend fun setUserJson(userJson: String)  = SharedPref.setUserJson(userJson)

    override fun getUserJson(): LiveData<String?> {
        val userJson = SharedPref.getUserJson()
        _userInfoMutableLiveData.value = userJson
        return _userInfoMutableLiveData
    }

}