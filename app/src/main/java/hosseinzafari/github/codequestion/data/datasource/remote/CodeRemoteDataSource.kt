package hosseinzafari.github.codequestion.data.datasource.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hosseinzafari.github.codequestion.data.main.CodeMain
import hosseinzafari.github.codequestion.struct.CodeModel
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.ui.data.main.UserMain
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.network.Api
import hosseinzafari.github.codequestion.ui.network.api
import hosseinzafari.github.codequestion.ui.network.retrofit
import hosseinzafari.github.codequestion.ui.struct.UserModel
import network.core.getApi

/*

@created in 25/07/2020 - 12:13 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class CodeRemoteDataSource : CodeMain {

    private var _bestCodeRemoteDataSource = MutableLiveData<List<CodeModel>>()
    private var _allCodeRemoteDataSource = MutableLiveData<ResponseStdModel>()
    private var _changeScore = MutableLiveData<ResponseStdModel>()
    private var _addCode = MutableLiveData<ResponseStdModel>()


    override suspend fun getBestCode(): LiveData<List<CodeModel>> {
         log("CodeRemoteDataSource thread : " + Thread.currentThread().name )
        _bestCodeRemoteDataSource.postValue(api.getBestCode())
        return _bestCodeRemoteDataSource
    }

    override suspend fun getAllCode(category: Int): LiveData<ResponseStdModel> {
        _allCodeRemoteDataSource.postValue(api.getAllCodes(category))
        return _allCodeRemoteDataSource
    }

    override suspend fun changeScore(isAdd: Boolean, codeId: Int): LiveData<ResponseStdModel> {
        val score = if(isAdd) 1 else 0
        val result = api.changeScore(score , codeId)
        _changeScore.postValue(result)
        return _changeScore
    }

    override suspend fun addCode(title: String , text: String , source: String): LiveData<ResponseStdModel> {
        val result = api.addCode(title , text , source)
        Log.i("TEHRAN" , "" + result)
        _addCode.postValue(result)
        return _addCode
    }

}