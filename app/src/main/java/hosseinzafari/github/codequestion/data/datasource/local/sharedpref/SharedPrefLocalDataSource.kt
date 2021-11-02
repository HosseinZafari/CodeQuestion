package hosseinzafari.github.codequestion.data.datasource.local.sharedpref

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hosseinzafari.github.codequestion.data.main.SharedPrefMain

class SharedPrefLocalDataSource : SharedPrefMain {
    private val _tokenMutableLiveData = MutableLiveData<String?>()
    private val _userInfoMutableLiveData = MutableLiveData<String?>()
    private val _userRoll = MutableLiveData<String>()
    
    override suspend fun setToken(token: String) = SharedPref.setToken(token)

    override fun getToken(): LiveData<String?> {
        _tokenMutableLiveData.postValue(SharedPref.getToken())
        return _tokenMutableLiveData
    }

    override suspend fun setUserJson(userJson: String)  = SharedPref.setUserJson(userJson)
    
    override suspend fun setRole(role: String) = SharedPref.setRole(role)
    
    override fun getRole(): LiveData<String> {
        _userRoll.postValue(SharedPref.getRole())
        return _userRoll
    }
    
    override fun getUserJson(): LiveData<String?> {
        val userJson = SharedPref.getUserJson()
        _userInfoMutableLiveData.value = userJson
        return _userInfoMutableLiveData
    }

}