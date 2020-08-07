package hosseinzafari.github.codequestion.ui.data.repository

import androidx.lifecycle.LiveData
import hosseinzafari.github.codequestion.data.main.CodeMain
import hosseinzafari.github.codequestion.struct.CodeModel
import hosseinzafari.github.codequestion.data.datasource.remote.CodeRemoteDataSource

/*
    @created in 25/07/2020 - 12:23 PM
    @project Code Question
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

class CodeRepository : CodeMain{
    private val codeRemote = CodeRemoteDataSource()

    override suspend fun getBestCode(): LiveData<List<CodeModel>> {
        return codeRemote.getBestCode()
    }

}