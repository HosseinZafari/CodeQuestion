package hosseinzafari.github.codequestion.ui.data.repository

import androidx.lifecycle.LiveData
import hosseinzafari.github.codequestion.data.datasource.remote.RulesRemoteDataSource
import hosseinzafari.github.codequestion.data.main.RulesMain
import hosseinzafari.github.codequestion.struct.ResponseStdModel

/*
    @created in 25/07/2020 - 12:23 PM
    @project Code Question
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

class RulesRepository : RulesMain {
    private val rulesRemote = RulesRemoteDataSource()

    override suspend fun getRules(): LiveData<ResponseStdModel> {
        return rulesRemote.getRules()
    }

}