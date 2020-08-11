package hosseinzafari.github.codequestion.data.datasource.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hosseinzafari.github.codequestion.data.main.RulesMain
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.ui.network.api

/*

@created in 11/08/2020 - 02:14 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class RulesRemoteDataSource : RulesMain {

    private val _rulesMutableLiveData = MutableLiveData<ResponseStdModel>()

    override suspend fun getRules(): LiveData<ResponseStdModel> {
        _rulesMutableLiveData.postValue(api.getRules())
        return _rulesMutableLiveData
    }

}