package hosseinzafari.github.codequestion.data.datasource.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hosseinzafari.github.codequestion.data.main.CodeMain
import hosseinzafari.github.codequestion.struct.CodeModel
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

class CodeRemoteDataSource : CodeMain {

    private var _codeRemoteDataSource = MutableLiveData<List<CodeModel>>()

    override suspend fun getBestCode(): LiveData<List<CodeModel>> {
         log("CodeRemoteDataSource thread : " + Thread.currentThread().name )
        _codeRemoteDataSource.postValue(api.getBestCode())
        return _codeRemoteDataSource
    }


}