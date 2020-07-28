package hosseinzafari.github.codequestion.ui.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

     override suspend fun getBestUser(): LiveData<List<UserModel>>  {
         log("remoteDataSource thread : " + Thread.currentThread().name )
        _bestUserLiveData.postValue(api.getBestUser())
        return _bestUserLiveData
    }

}