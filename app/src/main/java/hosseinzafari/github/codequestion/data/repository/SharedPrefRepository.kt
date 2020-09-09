package hosseinzafari.github.codequestion.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hosseinzafari.github.codequestion.data.datasource.local.SharedPrefLocalDataSource
import hosseinzafari.github.codequestion.data.main.SharedPrefMain
import hosseinzafari.github.codequestion.data.memory.SaveInMemory

/*

@created in 07/08/2020 - 11:02 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class SharedPrefRepository : SharedPrefMain {

    private val local = SharedPrefLocalDataSource()
    private val mutableLiveData by lazy { MutableLiveData<String>() }

    override fun getToken() = local.getToken()

    override suspend fun setToken(token: String)  {
        SaveInMemory.token = token
        local.setToken(token)
    }


    override fun getUserJson(): LiveData<String?>  {
        val userJsonLiveData = local.getUserJson()
        mutableLiveData.value = userJsonLiveData.value ?: SaveInMemory.userJsonInfo
        return mutableLiveData
    }


    override suspend fun setUserJson(userJson: String) {
        SaveInMemory.userJsonInfo = userJson
        local.setUserJson(userJson)
    }

}