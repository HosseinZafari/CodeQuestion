package hosseinzafari.github.codequestion.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hosseinzafari.github.codequestion.data.main.TokenMain
import hosseinzafari.github.codequestion.local.SharedPref

/*

@created in 07/08/2020 - 10:52 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class TokenLocalDataSource : TokenMain {
    private val _mutableLiveData = MutableLiveData<String?>()
    
    override suspend fun getToken(): LiveData<String?> {
        _mutableLiveData.postValue(SharedPref.getToken())
        return _mutableLiveData
    }    

    override suspend fun setToken(token: String) = SharedPref.setToken(token)
}