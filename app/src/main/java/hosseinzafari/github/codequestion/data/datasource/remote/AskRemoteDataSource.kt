package hosseinzafari.github.codequestion.data.datasource.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hosseinzafari.github.codequestion.data.main.AskMain
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.ui.network.api

/*

@created in 17/08/2020 - 10:21 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class AskRemoteDataSource : AskMain {
    private val _askMutableLiveData = MutableLiveData<ResponseStdModel>()

    override suspend fun ask(
        title: String,
        text: String,
        type: Int,
        course: String
    ): LiveData<ResponseStdModel> {
        _askMutableLiveData.postValue(api.ask(title , text , type , course))
        return _askMutableLiveData
    }
}