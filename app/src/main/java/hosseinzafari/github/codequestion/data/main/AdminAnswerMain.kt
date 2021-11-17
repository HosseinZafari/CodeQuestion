package hosseinzafari.github.codequestion.data.main

import androidx.lifecycle.LiveData
import hosseinzafari.github.codequestion.struct.AnswerModel
import hosseinzafari.github.codequestion.struct.ResponseStdModel

/*

@created in 10/11/2021 - 1:53 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

interface AdminAnswerMain {


    suspend fun response(model: AnswerModel): LiveData<ResponseStdModel>

}