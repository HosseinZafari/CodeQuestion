package hosseinzafari.github.codequestion.data.repository

import androidx.lifecycle.LiveData
import hosseinzafari.github.codequestion.data.datasource.remote.AdminAnswerDataSource
import hosseinzafari.github.codequestion.data.datasource.remote.AskRemoteDataSource
import hosseinzafari.github.codequestion.data.main.AdminAnswerMain
import hosseinzafari.github.codequestion.data.main.AskMain
import hosseinzafari.github.codequestion.struct.AnswerModel
import hosseinzafari.github.codequestion.struct.ResponseStdModel

/*

@created in 17/08/2020 - 10:25 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class AdminAnswerRepository : AdminAnswerMain {

    private val adminAnswerDataSource = AdminAnswerDataSource()


    override suspend fun response(model: AnswerModel) = adminAnswerDataSource.response(model)
}