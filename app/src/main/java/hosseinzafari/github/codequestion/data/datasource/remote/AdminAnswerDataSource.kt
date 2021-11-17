package hosseinzafari.github.codequestion.data.datasource.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hosseinzafari.github.codequestion.data.main.AdminAnswerMain
import hosseinzafari.github.codequestion.data.main.AskMain
import hosseinzafari.github.codequestion.struct.AnswerModel
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.ui.network.api

/*

@created in 17/08/2020 - 10:21 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class AdminAnswerDataSource : AdminAnswerMain {
    private val _answersMutableLiveData = MutableLiveData<ResponseStdModel>()

    override suspend fun response(model: AnswerModel): LiveData<ResponseStdModel> {
        _answersMutableLiveData.postValue(api.sendAnswer(model.toUser , model.title , model.questionId ,  model.text , model.type , model.course))
        return _answersMutableLiveData
    }
}